package dev.shermende.reference.db.repository.movement;

import dev.shermende.lib.dal.db.repository.QueryDslRepository;
import dev.shermende.reference.db.entity.movement.MovementRoute;
import dev.shermende.reference.db.entity.movement.QMovementRoute;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface MovementRouteRepository extends QueryDslRepository<MovementRoute, Long, QMovementRoute> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QMovementRoute root) {

    }

}
