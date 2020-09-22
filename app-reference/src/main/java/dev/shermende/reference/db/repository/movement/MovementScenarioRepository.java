package dev.shermende.reference.db.repository.movement;

import dev.shermende.lib.support.dal.db.repository.QueryDslRepository;
import dev.shermende.reference.db.entity.movement.MovementScenario;
import dev.shermende.reference.db.entity.movement.QMovementScenario;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource(collectionResourceRel = "data")
public interface MovementScenarioRepository extends QueryDslRepository<MovementScenario, Long, QMovementScenario> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QMovementScenario root) {

    }

    @Override
    @PreAuthorize(value = "hasAnyAuthority('ROLE_ROOT')")
    <S extends MovementScenario> @NotNull S save(@NotNull S s);

    @Override
    @PreAuthorize(value = "hasAnyAuthority('ROLE_ROOT')")
    void delete(@NotNull MovementScenario person);

    @Override
    @PreAuthorize(value = "hasAnyAuthority('ROLE_ROOT')")
    void deleteAll(@NotNull Iterable<? extends MovementScenario> persons);

    @Override
    @PreAuthorize(value = "hasAnyAuthority('ROLE_ROOT')")
    void deleteAll();

    @Override
    @PreAuthorize(value = "hasAnyAuthority('ROLE_ROOT')")
    void deleteById(@NotNull Long id);

}
