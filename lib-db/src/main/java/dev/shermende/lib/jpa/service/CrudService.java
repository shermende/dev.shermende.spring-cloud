package dev.shermende.lib.jpa.service;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * entity crud service
 *
 * @param <E> entity
 * @param <I> id
 */
public interface CrudService<E, I> {

    E save(E entity);

    boolean exists(Predicate predicate);

    boolean existsById(I p);

    boolean notExistsById(I p);

    Optional<E> findOne(Predicate predicate);

    Optional<E> findById(I id);

    /**
     * entity lazy load. only id except fields
     */
    E getById(I id);

    /**
     * entity graph
     */
    Optional<E> findByIdWithDetails(I id);

    Page<E> findAll(Pageable pageable);

    Page<E> findAll(Predicate predicate, Pageable pageable);

    List<E> findAll(Predicate predicate);

    void delete(E entity);

}
