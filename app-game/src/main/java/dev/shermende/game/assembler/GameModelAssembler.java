package dev.shermende.game.assembler;

import dev.shermende.game.controller.GameController;
import dev.shermende.game.db.entity.Game;
import dev.shermende.game.model.*;
import dev.shermende.game.service.feign.TranslateService;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class GameModelAssembler extends RepresentationModelAssemblerSupport<Game, GameModel> {

    private final TranslateService translateService;

    public GameModelAssembler(
        TranslateService translateService
    ) {
        super(GameController.class, GameModel.class);
        this.translateService = translateService;
    }

    @Override
    public @NotNull GameModel toModel(@NotNull Game entity) {
        return GameModel
            .builder()
            .id(entity.getId())
            .scenario(getScenario(entity))
            .reason(getReason(entity))
            .point(getPoint(entity))
            .build();
    }

    private MovementScenarioModel getScenario(@NotNull Game entity) {
        return MovementScenarioModel
            .builder()
            .id(entity.getScenarioId())
            .title(getTitle(MovementScenarioModel.class, entity.getScenarioId()))
            .description(getDescription(MovementScenarioModel.class, entity.getScenarioId()))
            .build();
    }

    private MovementReasonModel getReason(@NotNull Game entity) {
        return MovementReasonModel
            .builder()
            .id(entity.getReasonId())
            .title(getTitle(MovementReasonModel.class, entity.getReasonId()))
            .description(getDescription(MovementReasonModel.class, entity.getReasonId()))
            .build();
    }

    private MovementPointModel getPoint(@NotNull Game entity) {
        return MovementPointModel
            .builder()
            .id(entity.getPointId())
            .title(getTitle(MovementPointModel.class, entity.getPointId()))
            .description(getDescription(MovementPointModel.class, entity.getPointId()))
            .build();
    }

    protected String getTitle(@NotNull Class<?> clazz, @NotNull Long id) {
        final String key = getTitleKey(clazz, id);
        return translateService.findOneByKey(LocaleContextHolder.getLocale().getLanguage(), key)
            .orElse(TranslateModel.builder().value(key).build()).getValue();
    }

    protected String getDescription(@NotNull Class<?> clazz, @NotNull Long id) {
        final String key = getDescriptionKey(clazz, id);
        return translateService.findOneByKey(LocaleContextHolder.getLocale().getLanguage(), key)
            .orElse(TranslateModel.builder().value(key).build()).getValue();
    }

    private String getTitleKey(@NotNull Class<?> clazz, @NotNull Long id) {
        return String.format("%s.title.%d", clazz.getSimpleName(), id);
    }

    private String getDescriptionKey(@NotNull Class<?> clazz, @NotNull Long id) {
        return String.format("%s.description.%d", clazz.getSimpleName(), id);
    }

}