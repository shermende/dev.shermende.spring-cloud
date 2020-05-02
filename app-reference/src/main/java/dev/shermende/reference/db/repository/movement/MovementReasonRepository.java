package dev.shermende.reference.db.repository.movement;

import dev.shermende.lib.db.repository.QueryDslRepository;
import dev.shermende.reference.db.entity.movement.MovementReason;
import dev.shermende.reference.db.entity.movement.QMovementReason;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "data")
public interface MovementReasonRepository extends QueryDslRepository<MovementReason, Long, QMovementReason> {

    @Override
    @Query("SELECT e FROM MovementReason e WHERE e.id = :id")
    Optional<MovementReason> findWithDetails(Long id);

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QMovementReason root) {

    }

    @Override
    @PreAuthorize(value = "hasPermission('MOVEMENT_REASON', 'FULL')")
    <S extends MovementReason> @NotNull S save(@NotNull S s);

    @Override
    @PreAuthorize(value = "hasPermission('MOVEMENT_REASON', 'FULL')")
    void delete(@NotNull MovementReason person);

    @Override
    @PreAuthorize(value = "hasPermission('MOVEMENT_REASON', 'FULL')")
    void deleteAll(@NotNull Iterable<? extends MovementReason> persons);

    @Override
    @PreAuthorize(value = "hasPermission('MOVEMENT_REASON', 'FULL')")
    void deleteAll();

    @Override
    @PreAuthorize(value = "hasPermission('MOVEMENT_REASON', 'FULL')")
    void deleteById(@NotNull Long id);

}
