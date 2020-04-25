package dev.shermende.reference.assembler;

import dev.shermende.lib.model.reference.MovementScenarioModel;
import dev.shermende.reference.controller.MovementScenarioController;
import dev.shermende.reference.db.entity.movement.MovementPoint;
import dev.shermende.reference.db.entity.movement.MovementReason;
import dev.shermende.reference.db.entity.movement.MovementScenario;
import dev.shermende.reference.db.repository.TranslateRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class MovementScenarioModelAssembler extends AbstractTranslateAssembler<MovementScenario, MovementScenarioModel> {

    public MovementScenarioModelAssembler(
        TranslateRepository translateRepository
    ) {
        super(translateRepository, MovementScenarioController.class, MovementScenarioModel.class);
    }

    @NotNull
    @Override
    public MovementScenarioModel toModel(@NotNull MovementScenario entity) {
        return MovementScenarioModel.builder()
            .id(entity.getId())
            .title(entity.getTitle())
            .description(entity.getDescription())
            .reasonId(entity.getReasonId())
            .pointId(entity.getPointId())
            .reason(getTranslate(MovementReason.class, entity.getReasonId()))
            .point(getTranslate(MovementPoint.class, entity.getPointId()))
            .build();
    }

}
