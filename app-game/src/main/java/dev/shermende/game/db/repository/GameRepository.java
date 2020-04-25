package dev.shermende.game.db.repository;

import dev.shermende.game.db.entity.Game;
import dev.shermende.game.db.entity.QGame;
import dev.shermende.lib.db.repository.QueryDslRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.Optional;

public interface GameRepository extends QueryDslRepository<Game, Long, QGame> {

    @Override
    @Query("SELECT e FROM Game e WHERE e.id = :id")
    Optional<Game> findWithDetails(Long id);

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QGame root) {

    }

}
