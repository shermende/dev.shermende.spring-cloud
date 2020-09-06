package dev.shermende.reference.db.repository.movement;

import dev.shermende.lib.support.db.repository.QueryDslRepository;
import dev.shermende.reference.db.entity.movement.MovementRoute;
import dev.shermende.reference.db.entity.movement.QMovementRoute;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource(collectionResourceRel = "data")
public interface MovementRouteRepository extends QueryDslRepository<MovementRoute, Long, QMovementRoute> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QMovementRoute root) {

    }

    @Override
    @PreAuthorize(value = "hasAnyAuthority('ROLE_ROOT')")
    <S extends MovementRoute> @NotNull S save(@NotNull S s);

    @Override
    @PreAuthorize(value = "hasAnyAuthority('ROLE_ROOT')")
    void delete(@NotNull MovementRoute person);

    @Override
    @PreAuthorize(value = "hasAnyAuthority('ROLE_ROOT')")
    void deleteAll(@NotNull Iterable<? extends MovementRoute> persons);

    @Override
    @PreAuthorize(value = "hasAnyAuthority('ROLE_ROOT')")
    void deleteAll();

    @Override
    @PreAuthorize(value = "hasAnyAuthority('ROLE_ROOT')")
    void deleteById(@NotNull Long id);

}
