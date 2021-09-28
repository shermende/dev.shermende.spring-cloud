package dev.shermende.game.assembler;

import dev.shermende.game.controller.GameController;
import dev.shermende.game.db.entity.Game;
import dev.shermende.game.model.GameModel;
import dev.shermende.game.model.PointDescriptionModel;
import dev.shermende.game.model.ReasonDescriptionModel;
import dev.shermende.game.resource.BalabobaRequestResource;
import dev.shermende.game.service.PointDescriptionService;
import dev.shermende.game.service.ReasonDescriptionService;
import dev.shermende.game.service.feign.BalabobaService;
import dev.shermende.reference.lib.api.MovementPointApiService;
import dev.shermende.reference.lib.api.MovementReasonApiService;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class GameModelAssembler extends RepresentationModelAssemblerSupport<Game, GameModel> {

    private final BalabobaService service;
    private final MovementPointApiService pointApiService;
    private final MovementReasonApiService reasonApiService;
    private final PointDescriptionService pointDescriptionService;
    private final ReasonDescriptionService reasonDescriptionService;

    public GameModelAssembler(
            BalabobaService service,
            MovementPointApiService pointApiService,
            MovementReasonApiService reasonApiService,
            PointDescriptionService pointDescriptionService,
            ReasonDescriptionService reasonDescriptionService
    ) {
        super(GameController.class, GameModel.class);
        this.service = service;
        this.pointApiService = pointApiService;
        this.reasonApiService = reasonApiService;
        this.pointDescriptionService = pointDescriptionService;
        this.reasonDescriptionService = reasonDescriptionService;
    }

    @Override
    public @NotNull GameModel toModel(
            @NotNull Game entity
    ) {
        return GameModel
                .builder()
                .id(entity.getId())
                .source(getPointDescription(entity))
                .reason(getReasonDescription(entity))
                .target(getTargetDescription(entity))
                .build();
    }

    private PointDescriptionModel getPointDescription(
            @NotNull Game entity
    ) {
        if (Objects.isNull(entity.getSourcePointId())) return null;

        val pointDescription = pointDescriptionService.findByGameIdAndPointId(
                entity.getId(),
                entity.getSourcePointId()
        ).orElseGet(() -> {
            val point = pointApiService.findById(entity.getSourcePointId());
            return pointDescriptionService.create(
                    entity.getId(),
                    entity.getSourcePointId(),
                    point.getIntro(),
                    service.introspect(BalabobaRequestResource.builder()
                            .query(point.getIntro())
                            .build()).orElseThrow(IllegalStateException::new).getText()
            );
        });
        return PointDescriptionModel.builder()
                .id(pointDescription.getId())
                .intro(pointDescription.getIntro())
                .description(pointDescription.getDescription())
                .build();
    }

    private ReasonDescriptionModel getReasonDescription(
            @NotNull Game entity
    ) {
        val reasonDescription = reasonDescriptionService.findByGameIdAndPointId(
                entity.getId(),
                entity.getReasonId()
        ).orElseGet(() -> {
            val reason = reasonApiService.findById(entity.getReasonId());
            return reasonDescriptionService.create(
                    entity.getId(),
                    entity.getReasonId(),
                    reason.getIntro(),
                    service.introspect(BalabobaRequestResource.builder()
                            .query(reason.getIntro())
                            .build()).orElseThrow(IllegalStateException::new).getText()
            );
        });
        return ReasonDescriptionModel.builder()
                .id(reasonDescription.getId())
                .intro(reasonDescription.getIntro())
                .description(reasonDescription.getDescription())
                .build();
    }

    private PointDescriptionModel getTargetDescription(
            @NotNull Game entity
    ) {
        val pointDescription = pointDescriptionService.findByGameIdAndPointId(
                entity.getId(),
                entity.getTargetPointId()
        ).orElseGet(() -> {
            val point = pointApiService.findById(entity.getTargetPointId());
            return pointDescriptionService.create(
                    entity.getId(),
                    entity.getTargetPointId(),
                    point.getIntro(),
                    service.introspect(BalabobaRequestResource.builder()
                            .query(point.getIntro())
                            .build()).orElseThrow(IllegalStateException::new).getText()
            );
        });
        return PointDescriptionModel.builder()
                .id(pointDescription.getId())
                .intro(pointDescription.getIntro())
                .description(pointDescription.getDescription())
                .build();
    }

}
