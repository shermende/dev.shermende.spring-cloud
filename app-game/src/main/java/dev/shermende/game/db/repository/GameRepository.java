package dev.shermende.game.db.repository;

import dev.shermende.game.db.entity.Game;
import dev.shermende.game.db.entity.QGame;
import dev.shermende.lib.support.dal.db.repository.QueryDslRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "data")
public interface GameRepository extends QueryDslRepository<Game, Long, QGame> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QGame root) {

    }

}