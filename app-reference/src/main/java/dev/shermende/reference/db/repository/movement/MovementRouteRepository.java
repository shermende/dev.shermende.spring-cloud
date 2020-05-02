package dev.shermende.reference.db.repository.movement;

import dev.shermende.lib.db.repository.QueryDslRepository;
import dev.shermende.reference.db.entity.movement.MovementRoute;
import dev.shermende.reference.db.entity.movement.QMovementRoute;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "data")
public interface MovementRouteRepository extends QueryDslRepository<MovementRoute, Long, QMovementRoute> {

    @Override
    @Query("SELECT e FROM MovementRoute e WHERE e.id = :id")
    Optional<MovementRoute> findWithDetails(Long id);

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QMovementRoute root) {

    }

    @Override
    @PreAuthorize(value = "hasPermission('MOVEMENT_POINT', 'FULL')")
    <S extends MovementRoute> @NotNull S save(@NotNull S s);

    @Override
    @PreAuthorize(value = "hasPermission('MOVEMENT_POINT', 'FULL')")
    void delete(@NotNull MovementRoute person);

    @Override
    @PreAuthorize(value = "hasPermission('MOVEMENT_POINT', 'FULL')")
    void deleteAll(@NotNull Iterable<? extends MovementRoute> persons);

    @Override
    @PreAuthorize(value = "hasPermission('MOVEMENT_POINT', 'FULL')")
    void deleteAll();

    @Override
    @PreAuthorize(value = "hasPermission('MOVEMENT_POINT', 'FULL')")
    void deleteById(@NotNull Long id);

}
