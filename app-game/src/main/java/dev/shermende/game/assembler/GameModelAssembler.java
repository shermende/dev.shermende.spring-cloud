package dev.shermende.game.assembler;

import dev.shermende.game.controller.GameController;
import dev.shermende.game.db.entity.Game;
import dev.shermende.game.model.GameModel;
import dev.shermende.game.resource.BalabobaRequestResource;
import dev.shermende.game.service.feign.BalabobaService;
import dev.shermende.reference.lib.api.MovementPointApiService;
import dev.shermende.reference.lib.api.MovementReasonApiService;
import dev.shermende.reference.lib.model.MovementPointModel;
import dev.shermende.reference.lib.model.MovementReasonModel;
import dev.shermende.reference.lib.model.MovementScenarioModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class GameModelAssembler extends RepresentationModelAssemblerSupport<Game, GameModel> {

    private final BalabobaService service;
    private final MovementReasonApiService reasonApiService;
    private final MovementPointApiService movementPointService;

    public GameModelAssembler(
            BalabobaService service,
            MovementReasonApiService reasonApiService,
            MovementPointApiService movementPointService
    ) {
        super(GameController.class, GameModel.class);
        this.service = service;
        this.reasonApiService = reasonApiService;
        this.movementPointService = movementPointService;
    }

    @Override
    public @NotNull GameModel toModel(
            @NotNull Game entity
    ) {
        return GameModel
                .builder()
                .id(entity.getId())
                .scenario(getScenario(entity))
//                .source(getSourcePoint(entity))
                .reason(getReason(entity))
                .target(getTargetPoint(entity))
                .build();
    }

    private MovementScenarioModel getScenario(@NotNull Game entity) {
        return MovementScenarioModel
                .builder()
                .id(entity.getScenarioId())
                .build();
    }

    private MovementPointModel getSourcePoint(
            @NotNull Game entity
    ) {
        final MovementPointModel movementPointModel = movementPointService.findById(entity.getSourcePointId());
        return movementPointModel
                .setIntro(movementPointModel.getIntro() + " " + service.introspect(BalabobaRequestResource.builder()
                        .query(movementPointModel.getIntro())
                        .build()).orElseThrow(IllegalStateException::new).getText());
    }

    private MovementReasonModel getReason(
            @NotNull Game entity
    ) {
        final MovementReasonModel reason = reasonApiService.findById(entity.getReasonId());
        return reason
                .setIntro(reason.getIntro() + " " + service.introspect(BalabobaRequestResource.builder()
                        .query(reason.getIntro())
                        .build()).orElseThrow(IllegalStateException::new).getText());
    }

    private MovementPointModel getTargetPoint(
            @NotNull Game entity
    ) {
        final MovementPointModel movementPointModel = movementPointService.findById(entity.getTargetPointId());
        return movementPointModel
                .setIntro(movementPointModel.getIntro() + " " + service.introspect(BalabobaRequestResource.builder()
                        .query(movementPointModel.getIntro())
                        .build()).orElseThrow(IllegalStateException::new).getText());
    }

}
