package dev.shermende.game.service.crud;

import com.querydsl.core.types.Predicate;
import dev.shermende.game.db.entity.Game;
import dev.shermende.lib.dal.service.CrudService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface GameCrudService extends CrudService<Game, Long> {

    @NotNull
    Page<Game> findAllByAuthentication(
        @NotNull Authentication authentication,
        @Nullable Predicate predicate,
        @NotNull Pageable pageable
    );

}
