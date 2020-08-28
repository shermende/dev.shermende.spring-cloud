package dev.shermende.game.db.repository;

import dev.shermende.game.db.entity.Game;
import dev.shermende.game.db.entity.QGame;
import dev.shermende.lib.support.db.repository.QueryDslRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource(collectionResourceRel = "data")
public interface GameRepository extends QueryDslRepository<Game, Long, QGame> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QGame root) {

    }

    @Override
    @PreAuthorize(value = "hasPermission('GAME', 'FULL')")
    void delete(@NotNull Game person);

    @Override
    @PreAuthorize(value = "hasPermission('GAME', 'FULL')")
    void deleteAll(@NotNull Iterable<? extends Game> persons);

    @Override
    @PreAuthorize(value = "hasPermission('GAME', 'FULL')")
    void deleteAll();

    @Override
    @PreAuthorize(value = "hasPermission('GAME', 'FULL')")
    void deleteById(@NotNull Long id);

}
