package dev.shermende.authorization.db.repository;

import dev.shermende.authorization.db.entity.QUser;
import dev.shermende.authorization.db.entity.User;
import dev.shermende.lib.support.dal.db.repository.QueryDslRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface UserRepository extends QueryDslRepository<User, Long, QUser> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QUser root) {

    }

}
