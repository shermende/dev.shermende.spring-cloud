package dev.shermende.reference.assembler;

import dev.shermende.lib.model.reference.MovementRouteModel;
import dev.shermende.reference.controller.MovementRouteController;
import dev.shermende.reference.db.entity.movement.MovementRoute;
import dev.shermende.reference.db.entity.movement.MovementPoint;
import dev.shermende.reference.db.entity.movement.MovementReason;
import dev.shermende.reference.db.repository.TranslateRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class MovementRouteModelAssembler extends AbstractTranslateAssembler<MovementRoute, MovementRouteModel> {

    public MovementRouteModelAssembler(
        TranslateRepository translateRepository
    ) {
        super(translateRepository, MovementRouteController.class, MovementRouteModel.class);
    }

    @NotNull
    @Override
    public MovementRouteModel toModel(@NotNull MovementRoute entity) {
        return MovementRouteModel.builder()
            .id(entity.getId())
            .sourcePointId(entity.getSourcePointId())
            .reasonId(entity.getReasonId())
            .targetPointId(entity.getTargetPointId())
            .sourcePoint(getTranslate(MovementPoint.class, entity.getSourcePointId()))
            .reason(getTranslate(MovementReason.class, entity.getReasonId()))
            .targetPoint(getTranslate(MovementPoint.class, entity.getTargetPointId()))
            .build();
    }

}
