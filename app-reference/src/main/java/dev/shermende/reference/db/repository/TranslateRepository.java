package dev.shermende.reference.db.repository;

import dev.shermende.lib.db.repository.QueryDslRepository;
import dev.shermende.reference.db.entity.QTranslate;
import dev.shermende.reference.db.entity.Translate;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "data")
public interface TranslateRepository extends QueryDslRepository<Translate, Long, QTranslate> {

    @Override
    @Query("SELECT e FROM Translate e WHERE e.id = :id")
    Optional<Translate> findWithDetails(Long id);

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QTranslate root) {

    }

    @Override
    @PreAuthorize(value = "hasPermission('TRANSLATE', 'FULL')")
    <S extends Translate> @NotNull S save(@NotNull S s);

    @Override
    @PreAuthorize(value = "hasPermission('TRANSLATE', 'FULL')")
    void delete(@NotNull Translate person);

    @Override
    @PreAuthorize(value = "hasPermission('TRANSLATE', 'FULL')")
    void deleteAll(@NotNull Iterable<? extends Translate> persons);

    @Override
    @PreAuthorize(value = "hasPermission('TRANSLATE', 'FULL')")
    void deleteAll();

    @Override
    @PreAuthorize(value = "hasPermission('TRANSLATE', 'FULL')")
    void deleteById(@NotNull Long id);

}
