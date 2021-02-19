package dev.shermende.reference.service;

import dev.shermende.lib.dal.service.CrudService;
import dev.shermende.reference.db.entity.movement.MovementRoute;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MovementRouteService extends CrudService<MovementRoute, Long> {

    Page<MovementRoute> findAllBySourcePointId(
        @NotNull Pageable pageable,
        @NotNull Long sourcePointId
    );

    Optional<MovementRoute> findOneBySourcePointIdAndReasonId(
        @NotNull Long sourcePointId,
        @NotNull Long reasonId
    );

}
