package dev.shermende.reference.db.repository.movement;

import dev.shermende.lib.support.dal.db.repository.QueryDslRepository;
import dev.shermende.reference.db.entity.movement.MovementPoint;
import dev.shermende.reference.db.entity.movement.QMovementPoint;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource(collectionResourceRel = "data")
public interface MovementPointRepository extends QueryDslRepository<MovementPoint, Long, QMovementPoint> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QMovementPoint root) {

    }

    @Override
    @PreAuthorize(value = "hasAnyAuthority('ROLE_ROOT')")
    <S extends MovementPoint> @NotNull S save(@NotNull S s);

    @Override
    @PreAuthorize(value = "hasAnyAuthority('ROLE_ROOT')")
    void delete(@NotNull MovementPoint person);

    @Override
    @PreAuthorize(value = "hasAnyAuthority('ROLE_ROOT')")
    void deleteAll(@NotNull Iterable<? extends MovementPoint> persons);

    @Override
    @PreAuthorize(value = "hasAnyAuthority('ROLE_ROOT')")
    void deleteAll();

    @Override
    @PreAuthorize(value = "hasAnyAuthority('ROLE_ROOT')")
    void deleteById(@NotNull Long id);

}
