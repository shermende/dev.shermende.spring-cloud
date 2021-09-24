package dev.shermende.game.service.impl;

import com.querydsl.core.types.Predicate;
import dev.shermende.game.db.entity.Game;
import dev.shermende.game.db.entity.GraphMap;
import dev.shermende.game.db.entity.QGame;
import dev.shermende.game.db.repository.GameRepository;
import dev.shermende.game.resource.GameCreateResource;
import dev.shermende.game.service.GameService;
import dev.shermende.game.service.GraphMapService;
import dev.shermende.game.service.graph.GraphService;
import dev.shermende.lib.dal.service.AbstractCrudService;
import dev.shermende.lib.security.model.impl.PrincipalUser;
import dev.shermende.reference.lib.api.MovementPointApiService;
import dev.shermende.reference.lib.api.MovementScenarioApiService;
import dev.shermende.reference.lib.model.MovementPointModel;
import dev.shermende.reference.lib.model.MovementScenarioModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class GameServiceImpl extends AbstractCrudService<Game, Long, QGame> implements GameService {

    private final MovementPointApiService pointApiService;
    private final MovementScenarioApiService scenarioService;
    private final GraphMapService graphMapService;
    private final GraphService graphService;

    public GameServiceImpl(
            GameRepository repository,
            MovementPointApiService pointApiService,
            MovementScenarioApiService scenarioService,
            GraphMapService graphMapService,
            GraphService graphService
    ) {
        super(repository);
        this.pointApiService = pointApiService;
        this.scenarioService = scenarioService;
        this.graphMapService = graphMapService;
        this.graphService = graphService;
    }

    @Override
    public @NotNull Page<Game> findAll(
            @NotNull Authentication authentication,
            @Nullable Predicate predicate,
            @NotNull Pageable pageable
    ) {
        final PrincipalUser auth = (PrincipalUser) authentication.getPrincipal();
        return findAll(QGame.game.userId.eq(auth.getId()).and(predicate), pageable);
    }

    @Override
    @Transactional
    public @NotNull Game create(
            @NotNull Authentication authentication,
            @NotNull GameCreateResource resource
    ) {
        final PrincipalUser auth = (PrincipalUser) authentication.getPrincipal();
        final MovementScenarioModel scenario = getScenario(resource.getScenarioId());
        final int count = Math.toIntExact(pointApiService.countByScenario(scenario.getId()));
        final MovementPointModel scenarioPointByIndex = pointApiService.findScenarioPointByIndex(scenario.getId(), new Random().nextInt(count));

        final Game save = save(Game.builder()
                .userId(auth.getId())
                .scenarioId(scenario.getId())
                .sourceId(scenarioPointByIndex.getId())
                .targetId(scenarioPointByIndex.getId())
                .build());

        graphService.generateGraph(count, 25)
                .forEach(graphEdge ->
                        graphMapService.save(GraphMap.builder()
                                .userId(auth.getId())
                                .gameId(save.getId())
                                .sourceId(pointApiService.findScenarioPointByIndex(scenario.getId(), graphEdge.getSource()).getId())
                                .targetId(pointApiService.findScenarioPointByIndex(scenario.getId(), graphEdge.getTarget()).getId())
                                .build()));

        return save;
    }

    @NotNull
    private MovementScenarioModel getScenario(
            @NotNull Long scenarioId
    ) {
        return scenarioService.findById(scenarioId);
    }

}
