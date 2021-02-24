package dev.shermende.game.service;

import dev.shermende.game.db.entity.Game;
import dev.shermende.game.db.entity.Route;
import dev.shermende.game.resource.GameCreateResource;
import dev.shermende.game.resource.GameMoveResource;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GameService {

    @NotNull
    @Transactional
    Game create(
        @NotNull Authentication authentication,
        @NotNull GameCreateResource resource
    );

    @NotNull
    Game move(
        @NotNull Authentication authentication,
        @NotNull Long gameId,
        @NotNull GameMoveResource resource
    );

    @NotNull
    List<Route> getAvailableRoutes(
        @NotNull Long gameId
    );

}