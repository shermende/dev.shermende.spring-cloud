package dev.shermende.reference.db.repository;

import dev.shermende.lib.dal.db.repository.QueryDslRepository;
import dev.shermende.reference.db.entity.QTranslate;
import dev.shermende.reference.db.entity.Translate;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "data")
public interface TranslateRepository extends QueryDslRepository<Translate, Long, QTranslate> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QTranslate root) {

    }

}