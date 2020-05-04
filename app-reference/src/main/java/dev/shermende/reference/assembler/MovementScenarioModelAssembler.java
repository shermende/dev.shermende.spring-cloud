package dev.shermende.reference.assembler;

import dev.shermende.reference.controller.MovementScenarioController;
import dev.shermende.reference.db.entity.movement.MovementScenario;
import dev.shermende.reference.model.MovementScenarioModel;
import dev.shermende.reference.service.TranslateService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class MovementScenarioModelAssembler extends AbstractTranslateAssembler<MovementScenario, MovementScenarioModel> {

    public MovementScenarioModelAssembler(
        TranslateService translateService
    ) {
        super(translateService, MovementScenarioController.class, MovementScenarioModel.class);
    }

    @NotNull
    @Override
    public MovementScenarioModel toModel(@NotNull MovementScenario entity) {
        return MovementScenarioModel.builder()
            .id(entity.getId())
            .title(getTitle(MovementScenarioModel.class, entity.getId()))
            .description(getDescription(MovementScenarioModel.class, entity.getId()))
            .reasonId(entity.getReasonId())
            .pointId(entity.getPointId())
            .build();
    }

}
