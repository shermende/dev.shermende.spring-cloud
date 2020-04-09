package dev.shermende.translation.db.repository.movement;

import dev.shermende.lib.jpa.repository.QueryDslRepository;
import dev.shermende.translation.db.entity.movement.MovementPoint;
import dev.shermende.translation.db.entity.movement.QMovementPoint;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface MovementPointRepository extends QueryDslRepository<MovementPoint, Long, QMovementPoint> {

    @Override
    @Query("SELECT e FROM MovementPoint e WHERE e.id = :id")
    Optional<MovementPoint> findWithDetails(Long id);

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QMovementPoint root) {

    }

}
