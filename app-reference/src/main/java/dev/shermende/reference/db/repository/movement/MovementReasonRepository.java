package dev.shermende.reference.db.repository.movement;

import dev.shermende.lib.support.db.repository.QueryDslRepository;
import dev.shermende.reference.db.entity.movement.MovementReason;
import dev.shermende.reference.db.entity.movement.QMovementReason;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource(collectionResourceRel = "data")
public interface MovementReasonRepository extends QueryDslRepository<MovementReason, Long, QMovementReason> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QMovementReason root) {

    }

    @Override
    @PreAuthorize(value = "hasAnyAuthority('ROLE_ROOT')")
    <S extends MovementReason> @NotNull S save(@NotNull S s);

    @Override
    @PreAuthorize(value = "hasAnyAuthority('ROLE_ROOT')")
    void delete(@NotNull MovementReason person);

    @Override
    @PreAuthorize(value = "hasAnyAuthority('ROLE_ROOT')")
    void deleteAll(@NotNull Iterable<? extends MovementReason> persons);

    @Override
    @PreAuthorize(value = "hasAnyAuthority('ROLE_ROOT')")
    void deleteAll();

    @Override
    @PreAuthorize(value = "hasAnyAuthority('ROLE_ROOT')")
    void deleteById(@NotNull Long id);

}
