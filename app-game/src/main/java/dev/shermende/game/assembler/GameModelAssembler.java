package dev.shermende.game.assembler;

import dev.shermende.game.controller.GameController;
import dev.shermende.game.db.entity.Game;
import dev.shermende.game.service.feign.TranslateService;
import dev.shermende.lib.model.game.GameModel;
import dev.shermende.lib.model.reference.MovementPointModel;
import dev.shermende.lib.model.reference.MovementReasonModel;
import dev.shermende.lib.model.reference.MovementScenarioModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class GameModelAssembler extends AbstractTranslateAssembler<Game, GameModel> {

    public GameModelAssembler(TranslateService translateService) {
        super(translateService, GameController.class, GameModel.class);
    }

    @Override
    public @NotNull GameModel toModel(@NotNull Game entity) {
        return GameModel
            .builder()
            .id(entity.getId())
            .userId(entity.getUserId())
            .scenarioId(entity.getScenarioId())
            .reasonId(entity.getReasonId())
            .pointId(entity.getPointId())
            .scenario(getTranslate(MovementScenarioModel.class, entity.getScenarioId()))
            .reason(getTranslate(MovementReasonModel.class, entity.getReasonId()))
            .point(getTranslate(MovementPointModel.class, entity.getPointId()))
            .build();
    }

}
