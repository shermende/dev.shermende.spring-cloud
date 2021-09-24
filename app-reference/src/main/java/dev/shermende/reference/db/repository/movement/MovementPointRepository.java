package dev.shermende.reference.db.repository.movement;

import dev.shermende.lib.dal.db.repository.QueryDslRepository;
import dev.shermende.reference.db.entity.movement.MovementPoint;
import dev.shermende.reference.db.entity.movement.QMovementPoint;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface MovementPointRepository extends QueryDslRepository<MovementPoint, Long, QMovementPoint> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QMovementPoint root) {

    }

}
