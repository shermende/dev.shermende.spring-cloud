package dev.shermende.reference.assembler;

import dev.shermende.reference.controller.MovementRouteController;
import dev.shermende.reference.db.entity.movement.MovementRoute;
import dev.shermende.reference.model.MovementReasonModel;
import dev.shermende.reference.model.MovementRouteModel;
import dev.shermende.reference.service.TranslateService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class MovementRouteModelAssembler extends AbstractTranslateAssembler<MovementRoute, MovementRouteModel> {

    public MovementRouteModelAssembler(
        TranslateService translateService
    ) {
        super(translateService, MovementRouteController.class, MovementRouteModel.class);
    }

    @NotNull
    @Override
    public MovementRouteModel toModel(@NotNull MovementRoute entity) {
        return MovementRouteModel.builder()
            .id(entity.getId())
            .title(getTitle(MovementReasonModel.class, entity.getReasonId()))
            .description(getDescription(MovementReasonModel.class, entity.getReasonId()))
            .sourcePointId(entity.getSourcePointId())
            .reasonId(entity.getReasonId())
            .targetPointId(entity.getTargetPointId())
            .build();
    }

}
