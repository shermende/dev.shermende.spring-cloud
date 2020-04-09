package dev.shermende.translation.db.repository;

import dev.shermende.lib.jpa.repository.QueryDslRepository;
import dev.shermende.translation.db.entity.QTranslate;
import dev.shermende.translation.db.entity.Translate;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface TranslateRepository extends QueryDslRepository<Translate, Long, QTranslate> {

    @Override
    @Query("SELECT e FROM Translate e WHERE e.id = :id")
    Optional<Translate> findWithDetails(Long id);

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QTranslate root) {

    }

}
