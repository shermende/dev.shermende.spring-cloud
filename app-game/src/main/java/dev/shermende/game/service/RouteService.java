package dev.shermende.game.service;

import dev.shermende.game.db.entity.Game;
import dev.shermende.game.db.entity.Route;
import dev.shermende.game.resource.RouteCreateResource;
import org.jetbrains.annotations.NotNull;
import org.springframework.transaction.annotation.Transactional;

public interface RouteService {

    @Transactional
    Game generateMap(
        @NotNull Game game
    );

    @NotNull
    Route create(
        @NotNull RouteCreateResource resource
    );

}