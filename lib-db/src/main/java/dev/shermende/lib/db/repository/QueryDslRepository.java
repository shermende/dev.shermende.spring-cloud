package dev.shermende.lib.db.repository;

import com.querydsl.core.types.EntityPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @param <E> entity
 * @param <I> id
 * @param <Q> query-dsl
 */
@NoRepositoryBean
public interface QueryDslRepository<E, I, Q extends EntityPath<?>>
        extends JpaRepository<E, I>, QuerydslPredicateExecutor<E>, QuerydslBinderCustomizer<Q> {

}
