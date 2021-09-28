package dev.shermende.game.db.repository;

import dev.shermende.game.db.entity.QReasonDescription;
import dev.shermende.game.db.entity.ReasonDescription;
import dev.shermende.lib.dal.db.repository.QueryDslRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface ReasonDescriptionRepository extends QueryDslRepository<ReasonDescription, Long, QReasonDescription> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QReasonDescription root) {

    }

}
