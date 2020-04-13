package dev.shermende.authorization.db.repository;

import dev.shermende.authorization.db.entity.QUser;
import dev.shermende.authorization.db.entity.User;
import dev.shermende.lib.db.repository.QueryDslRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.Optional;

public interface UserRepository extends QueryDslRepository<User, Long, QUser> {

    @Override
    @Query("SELECT e FROM User e WHERE e.id = :id")
    Optional<User> findWithDetails(Long id);

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QUser root) {

    }

}
