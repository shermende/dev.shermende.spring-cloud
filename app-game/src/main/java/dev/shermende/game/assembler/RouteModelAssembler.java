package dev.shermende.game.assembler;

import dev.shermende.game.controller.GameController;
import dev.shermende.game.db.entity.Route;
import dev.shermende.game.model.MovementPointModel;
import dev.shermende.game.model.MovementReasonModel;
import dev.shermende.game.model.RouteModel;
import dev.shermende.game.model.TranslateModel;
import dev.shermende.game.service.feign.TranslateService;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RouteModelAssembler extends RepresentationModelAssemblerSupport<Route, RouteModel> {

    private final TranslateService translateService;

    public RouteModelAssembler(
        TranslateService translateService
    ) {
        super(GameController.class, RouteModel.class);
        this.translateService = translateService;
    }

    @Override
    public @NotNull RouteModel toModel(
        @NotNull Route entity
    ) {
        return RouteModel
            .builder()
            .id(entity.getId())
            .sourcePoint(getPoint(entity.getSourcePointId()))
            .reason(getReason(entity.getReasonId()))
            .targetPoint(getPoint(entity.getTargetPointId()))
            .build();
    }

    private MovementReasonModel getReason(
        @NotNull Long id
    ) {
        return MovementReasonModel
            .builder()
            .id(id)
            .title(getTitle(MovementReasonModel.class, id))
            .description(getDescription(MovementReasonModel.class, id))
            .build();
    }

    private MovementPointModel getPoint(
        @NotNull Long id
    ) {
        return MovementPointModel
            .builder()
            .id(id)
            .title(getTitle(MovementPointModel.class, id))
            .description(getDescription(MovementPointModel.class, id))
            .build();
    }

    protected String getTitle(
        @NotNull Class<?> clazz,
        @NotNull Long id
    ) {
        final String key = getTitleKey(clazz, id);
        return translateService.findOneByKey(LocaleContextHolder.getLocale().getLanguage(), key)
            .orElse(TranslateModel.builder().value(key).build()).getValue();
    }

    protected String getDescription(
        @NotNull Class<?> clazz,
        @NotNull Long id
    ) {
        final String key = getDescriptionKey(clazz, id);
        return translateService.findOneByKey(LocaleContextHolder.getLocale().getLanguage(), key)
            .orElse(TranslateModel.builder().value(key).build()).getValue();
    }

    private String getTitleKey(
        @NotNull Class<?> clazz,
        @NotNull Long id
    ) {
        return String.format("%s.title.%d", clazz.getSimpleName(), id);
    }

    private String getDescriptionKey(
        @NotNull Class<?> clazz,
        @NotNull Long id
    ) {
        return String.format("%s.description.%d", clazz.getSimpleName(), id);
    }

}