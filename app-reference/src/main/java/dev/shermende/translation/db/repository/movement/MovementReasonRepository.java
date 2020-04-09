package dev.shermende.translation.db.repository.movement;

import dev.shermende.lib.jpa.repository.QueryDslRepository;
import dev.shermende.translation.db.entity.movement.MovementReason;
import dev.shermende.translation.db.entity.movement.QMovementReason;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface MovementReasonRepository extends QueryDslRepository<MovementReason, Long, QMovementReason> {

    @Override
    @Query("SELECT e FROM MovementReason e WHERE e.id = :id")
    Optional<MovementReason> findWithDetails(Long id);

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QMovementReason root) {

    }

}
