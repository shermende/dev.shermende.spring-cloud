package dev.shermende.reference.db.repository.movement;

import dev.shermende.lib.db.repository.QueryDslRepository;
import dev.shermende.reference.db.entity.movement.MovementScenario;
import dev.shermende.reference.db.entity.movement.QMovementScenario;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;

@RepositoryRestResource
public interface MovementScenarioRepository extends QueryDslRepository<MovementScenario, Long, QMovementScenario> {

    @Override
    @Query("SELECT e FROM MovementScenario e WHERE e.id = :id")
    Optional<MovementScenario> findWithDetails(Long id);

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QMovementScenario root) {

    }

    @Override
    @PreAuthorize(value = "hasPermission('MOVEMENT_SCENARIO', 'FULL')")
    <S extends MovementScenario> @NotNull S save(@NotNull S s);

    @Override
    @PreAuthorize(value = "hasPermission('MOVEMENT_SCENARIO', 'FULL')")
    void delete(@NotNull MovementScenario person);

    @Override
    @PreAuthorize(value = "hasPermission('MOVEMENT_SCENARIO', 'FULL')")
    void deleteAll(@NotNull Iterable<? extends MovementScenario> persons);

    @Override
    @PreAuthorize(value = "hasPermission('MOVEMENT_SCENARIO', 'FULL')")
    void deleteAll();

    @Override
    @PreAuthorize(value = "hasPermission('MOVEMENT_SCENARIO', 'FULL')")
    void deleteById(@NotNull Long id);

}
