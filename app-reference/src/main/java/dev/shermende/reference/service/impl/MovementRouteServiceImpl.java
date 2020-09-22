package dev.shermende.reference.service.impl;

import dev.shermende.lib.support.dal.service.AbstractCrudService;
import dev.shermende.reference.db.entity.movement.MovementRoute;
import dev.shermende.reference.db.entity.movement.QMovementRoute;
import dev.shermende.reference.db.repository.movement.MovementRouteRepository;
import dev.shermende.reference.service.MovementRouteService;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovementRouteServiceImpl extends AbstractCrudService<MovementRoute, Long, QMovementRoute>
    implements MovementRouteService {

    public MovementRouteServiceImpl(
        MovementRouteRepository repository
    ) {
        super(repository);
    }

    @Override
    public Page<MovementRoute> findAllBySourcePointId(
        @NotNull Pageable pageable,
        @NotNull Long sourcePointId
    ) {
        return findAll(QMovementRoute.movementRoute.sourcePointId.eq(sourcePointId), pageable);
    }

    @Override
    public Optional<MovementRoute> findOneBySourcePointIdAndReasonId(
        @NotNull Long sourcePointId,
        @NotNull Long reasonId
    ) {
        return findOne(
            QMovementRoute.movementRoute.sourcePointId.eq(sourcePointId)
                .and(QMovementRoute.movementRoute.reasonId.eq(reasonId)));
    }

}
