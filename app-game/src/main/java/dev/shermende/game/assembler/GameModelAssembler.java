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
    private final MovementPointApiService movementPointService;
    private final MovementReasonApiService movementReasonService;

    public GameModelAssembler(
            BalabobaService service,
            MovementPointApiService movementPointService,
            MovementReasonApiService movementReasonService
    ) {
        super(GameController.class, GameModel.class);
        this.service = service;
        this.movementPointService = movementPointService;
        this.movementReasonService = movementReasonService;
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
                .build();
    }

    private MovementReasonModel getReason(@NotNull Game entity) {
        final MovementReasonModel movementReasonModel = movementReasonService.findById(entity.getReasonId());
        return movementReasonModel
                .setIntro(movementReasonModel.getIntro() + " " + service.introspect(BalabobaRequestResource.builder()
                        .query(movementReasonModel.getIntro())
                        .build()).orElseThrow(IllegalStateException::new).getText());
    }

    private MovementPointModel getPoint(@NotNull Game entity) {
        final MovementPointModel movementPointModel = movementPointService.findById(entity.getReasonId());
        return movementPointModel
                .setIntro(movementPointModel.getIntro() + " " + service.introspect(BalabobaRequestResource.builder()
                        .query(movementPointModel.getIntro())
                        .build()).orElseThrow(IllegalStateException::new).getText());
    }

}
