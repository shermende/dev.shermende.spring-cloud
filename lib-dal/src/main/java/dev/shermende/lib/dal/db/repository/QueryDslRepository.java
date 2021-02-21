package dev.shermende.lib.dal.db.repository;

import com.querydsl.core.types.EntityPath;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @param <E> entity
 * @param <I> id
 * @param <Q> query-dsl
 */
@NoRepositoryBean
public interface QueryDslRepository<E, I, Q extends EntityPath<?>>
        extends JpaRepository<E, I>, QuerydslPredicateExecutor<E>, QuerydslBinderCustomizer<Q> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull Q root) {

    }

}
