package dev.shermende.translation.db.repository.movement;

import dev.shermende.lib.db.repository.QueryDslRepository;
import dev.shermende.translation.db.entity.movement.MovementMap;
import dev.shermende.translation.db.entity.movement.QMovementMap;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface MovementMapRepository extends QueryDslRepository<MovementMap, Long, QMovementMap> {

    @Override
    @Query("SELECT e FROM MovementMap e WHERE e.id = :id")
    Optional<MovementMap> findWithDetails(Long id);

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QMovementMap root) {

    }

}
