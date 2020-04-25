package dev.shermende.game.service.impl;

import com.querydsl.core.types.Predicate;
import dev.shermende.game.db.entity.Game;
import dev.shermende.game.db.entity.QGame;
import dev.shermende.game.db.repository.GameRepository;
import dev.shermende.game.service.feign.AuthorizationService;
import dev.shermende.game.service.feign.MovementRouteService;
import dev.shermende.game.service.feign.MovementScenarioService;
import dev.shermende.game.service.GameService;
import dev.shermende.lib.db.service.AbstractCrudService;
import dev.shermende.lib.model.reference.MovementRouteModel;
import dev.shermende.lib.model.reference.MovementScenarioModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class GameServiceImpl extends AbstractCrudService<Game, Long, QGame> implements GameService {

    private final MovementRouteService movementRouteService;
    private final MovementScenarioService scenarioService;
    private final AuthorizationService authorizationService;

    public GameServiceImpl(
        GameRepository repository,
        MovementRouteService movementRouteService,
        MovementScenarioService scenarioService,
        AuthorizationService authorizationService
    ) {
        super(repository);
        this.movementRouteService = movementRouteService;
        this.scenarioService = scenarioService;
        this.authorizationService = authorizationService;
    }

    @Override
    public Page<Game> findAll(Authentication authentication, Predicate predicate, Pageable pageable) {
        return findAll(QGame.game.userId.eq(authorizationService.introspect().getId()).and(predicate), pageable);
    }

    @Override
    public Game create(Authentication authentication, Long scenarioId) {
        final MovementScenarioModel scenario = scenarioService.findById(scenarioId).orElseThrow(EntityNotFoundException::new);
        return save(Game.builder()
            .userId(authorizationService.introspect().getId())
            .scenarioId(scenarioId)
            .reasonId(scenario.getReasonId())
            .pointId(scenario.getPointId())
            .build());
    }

    @Override
    public Game move(Authentication authentication, Long gameId, Long reasonId) {
        final Game game = findById(gameId).orElseThrow(EntityNotFoundException::new);
        final MovementRouteModel map = movementRouteService
            .findBySourcePointIdAndReasonId(game.getPointId(), reasonId).orElseThrow(EntityNotFoundException::new);
        game.setReasonId(map.getReasonId());
        game.setPointId(map.getTargetPointId());
        return save(game);
    }

}
