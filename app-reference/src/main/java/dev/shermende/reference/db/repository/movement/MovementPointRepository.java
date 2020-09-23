package dev.shermende.reference.db.repository.movement;

import dev.shermende.lib.support.dal.db.repository.QueryDslRepository;
import dev.shermende.reference.db.entity.movement.MovementPoint;
import dev.shermende.reference.db.entity.movement.QMovementPoint;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "data")
public interface MovementPointRepository extends QueryDslRepository<MovementPoint, Long, QMovementPoint> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QMovementPoint root) {

    }

}