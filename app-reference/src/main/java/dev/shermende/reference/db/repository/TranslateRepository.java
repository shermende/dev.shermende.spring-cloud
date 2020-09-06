package dev.shermende.reference.db.repository;

import dev.shermende.lib.support.db.repository.QueryDslRepository;
import dev.shermende.reference.db.entity.QTranslate;
import dev.shermende.reference.db.entity.Translate;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource(collectionResourceRel = "data")
public interface TranslateRepository extends QueryDslRepository<Translate, Long, QTranslate> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QTranslate root) {

    }

    @Override
    @PreAuthorize(value = "hasAnyAuthority('ROLE_ROOT')")
    <S extends Translate> @NotNull S save(@NotNull S s);

    @Override
    @PreAuthorize(value = "hasAnyAuthority('ROLE_ROOT')")
    void delete(@NotNull Translate person);

    @Override
    @PreAuthorize(value = "hasAnyAuthority('ROLE_ROOT')")
    void deleteAll(@NotNull Iterable<? extends Translate> persons);

    @Override
    @PreAuthorize(value = "hasAnyAuthority('ROLE_ROOT')")
    void deleteAll();

    @Override
    @PreAuthorize(value = "hasAnyAuthority('ROLE_ROOT')")
    void deleteById(@NotNull Long id);

}
