package dev.shermende.game.db.repository;

import dev.shermende.game.db.entity.GameRoute;
import dev.shermende.game.db.entity.QGameRoute;
import dev.shermende.lib.dal.db.repository.QueryDslRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface GameRouteRepository extends QueryDslRepository<GameRoute, Long, QGameRoute> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QGameRoute root) {

    }

}
