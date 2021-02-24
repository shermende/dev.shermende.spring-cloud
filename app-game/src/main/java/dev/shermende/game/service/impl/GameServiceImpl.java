package dev.shermende.game.service.impl;

import dev.shermende.game.db.entity.Game;
import dev.shermende.game.db.entity.Route;
import dev.shermende.game.exception.GameNotFoundException;
import dev.shermende.game.exception.RouteNotFoundException;
import dev.shermende.game.exception.ScenarioNotFoundException;
import dev.shermende.game.model.MovementScenarioModel;
import dev.shermende.game.resource.GameCreateResource;
import dev.shermende.game.resource.GameMoveResource;
import dev.shermende.game.service.GameService;
import dev.shermende.game.service.RouteService;
import dev.shermende.game.service.crud.GameCrudService;
import dev.shermende.game.service.crud.RouteCrudService;
import dev.shermende.game.service.feign.MovementScenarioService;
import dev.shermende.lib.security.model.impl.PrincipalUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final RouteService routeService;
    private final GameCrudService gameCrudService;
    private final RouteCrudService routeCrudService;
    private final MovementScenarioService scenarioService;

    @NotNull
    @Override
    @Transactional
    public Game create(
        @NotNull Authentication authentication,
        @NotNull GameCreateResource resource
    ) {
        final PrincipalUser auth = (PrincipalUser) authentication.getPrincipal();
        final MovementScenarioModel scenario =
            scenarioService.findById(resource.getScenarioId()).orElseThrow(ScenarioNotFoundException::new);
        final Game entity =
            routeService.generateMap(
                gameCrudService.save(Game.builder()
                    .userId(auth.getId())
                    .scenarioId(scenario.getId())
                    .reasonId(scenario.getReasonId())
                    .pointId(scenario.getPointId())
                    .build())
            );
        log.debug("[Game] [created] [{}]", entity);
        return entity;
    }

    @NotNull
    @Override
    public Game move(
        @NotNull Authentication authentication,
        @NotNull Long gameId,
        @NotNull GameMoveResource resource
    ) {
        final Route route =
            routeCrudService.findById(resource.getRouteId()).orElseThrow(RouteNotFoundException::new);
        final Game game = gameCrudService.findById(gameId).orElseThrow(GameNotFoundException::new);
        game.setReasonId(route.getReasonId());
        game.setPointId(route.getTargetPointId());
        log.debug("[Game] [moved] [{}]", game);
        return gameCrudService.save(game);
    }

    @NotNull
    @Override
    public List<Route> getAvailableRoutes(
        @NotNull Long gameId
    ) {
        final Game game = gameCrudService.findById(gameId).orElseThrow(EntityNotFoundException::new);
        return routeCrudService.getRoutesByGameIdAndSourcePointId(game.getId(), game.getPointId());
    }

}