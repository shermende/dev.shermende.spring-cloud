package dev.shermende.reference.db.repository.movement;

import dev.shermende.lib.db.repository.QueryDslRepository;
import dev.shermende.reference.db.entity.movement.MovementPoint;
import dev.shermende.reference.db.entity.movement.MovementReason;
import dev.shermende.reference.db.entity.movement.QMovementPoint;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;

@RepositoryRestResource
public interface MovementPointRepository extends QueryDslRepository<MovementPoint, Long, QMovementPoint> {

    @Override
    @Query("SELECT e FROM MovementPoint e WHERE e.id = :id")
    Optional<MovementPoint> findWithDetails(Long id);

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QMovementPoint root) {

    }

    @Override
    @PreAuthorize(value = "hasPermission('MOVEMENT_POINT', 'FULL')")
    <S extends MovementPoint> @NotNull S save(@NotNull S s);

    @Override
    @PreAuthorize(value = "hasPermission('MOVEMENT_POINT', 'FULL')")
    void delete(@NotNull MovementPoint person);

    @Override
    @PreAuthorize(value = "hasPermission('MOVEMENT_POINT', 'FULL')")
    void deleteAll(@NotNull Iterable<? extends MovementPoint> persons);

    @Override
    @PreAuthorize(value = "hasPermission('MOVEMENT_POINT', 'FULL')")
    void deleteAll();

    @Override
    @PreAuthorize(value = "hasPermission('MOVEMENT_POINT', 'FULL')")
    void deleteById(@NotNull Long id);

}
