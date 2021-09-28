package dev.shermende.game.db.repository;

import dev.shermende.game.db.entity.PointDescription;
import dev.shermende.game.db.entity.QPointDescription;
import dev.shermende.lib.dal.db.repository.QueryDslRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface PointDescriptionRepository extends QueryDslRepository<PointDescription, Long, QPointDescription> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QPointDescription root) {

    }

}
