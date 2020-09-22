package dev.shermende.game.service.impl;

import com.querydsl.core.types.Predicate;
import dev.shermende.game.db.entity.Game;
import dev.shermende.game.db.entity.QGame;
import dev.shermende.game.db.repository.GameRepository;
import dev.shermende.game.exception.GameNotFoundException;
import dev.shermende.game.exception.RouteNotFoundException;
import dev.shermende.game.exception.ScenarioNotFoundException;
import dev.shermende.game.model.MovementRouteModel;
import dev.shermende.game.model.MovementScenarioModel;
import dev.shermende.game.model.UserModel;
import dev.shermende.game.resource.GameCreateResource;
import dev.shermende.game.resource.GameMoveResource;
import dev.shermende.game.service.GameService;
import dev.shermende.game.service.feign.MovementRouteService;
import dev.shermende.game.service.feign.MovementScenarioService;
import dev.shermende.lib.support.service.AbstractCrudService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl extends AbstractCrudService<Game, Long, QGame> implements GameService {

    private final MovementRouteService movementRouteService;
    private final MovementScenarioService scenarioService;

    public GameServiceImpl(
        GameRepository repository,
        MovementRouteService movementRouteService,
        MovementScenarioService scenarioService
    ) {
        super(repository);
        this.movementRouteService = movementRouteService;
        this.scenarioService = scenarioService;
    }

    @Override
    public @NotNull Page<Game> findAll(
        @NotNull Authentication authentication,
        @Nullable Predicate predicate,
        @NotNull Pageable pageable
    ) {
        final UserModel auth = (UserModel) authentication.getPrincipal();
        return findAll(QGame.game.userId.eq(auth.getId()).and(predicate), pageable);
    }

    @Override
    public @NotNull Game create(
        @NotNull Authentication authentication,
        @NotNull GameCreateResource resource
    ) {
        final UserModel auth = (UserModel) authentication.getPrincipal();
        final MovementScenarioModel scenario = getScenario(resource.getScenarioId());
        return save(Game.builder()
            .userId(auth.getId())
            .scenarioId(scenario.getId())
            .reasonId(scenario.getReasonId())
            .pointId(scenario.getPointId())
            .build());
    }

    @Override
    public @NotNull Game move(
        @NotNull Authentication authentication,
        @NotNull Long gameId,
        @NotNull GameMoveResource resource
    ) {
        final MovementRouteModel route = getRoute(resource.getRouteId());
        final Game game = findById(gameId).orElseThrow(GameNotFoundException::new);
        game.setRouteId(route.getId());
        game.setReasonId(route.getReasonId());
        game.setPointId(route.getTargetPointId());
        return save(game);
    }

    @NotNull
    private MovementScenarioModel getScenario(
        @NotNull Long scenarioId
    ) {
        return scenarioService.findById(scenarioId).orElseThrow(ScenarioNotFoundException::new);
    }

    @NotNull
    private MovementRouteModel getRoute(
        @NotNull Long routeId
    ) {
        return movementRouteService.findById(routeId).orElseThrow(RouteNotFoundException::new);
    }

}
