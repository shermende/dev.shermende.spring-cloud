package dev.shermende.game.db.repository;

import dev.shermende.game.db.entity.GraphMap;
import dev.shermende.game.db.entity.QGraphMap;
import dev.shermende.lib.dal.db.repository.QueryDslRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface GraphMapRepository extends QueryDslRepository<GraphMap, Long, QGraphMap> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QGraphMap root) {

    }

}
