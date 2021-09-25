package dev.shermende.game.processor.gamecreateprocessor.step;

import dev.shermende.game.db.entity.GameRoute;
import dev.shermende.game.graph.service.GraphService;
import dev.shermende.game.processor.gamecreateprocessor.GameCreateProcessorCtx;
import dev.shermende.game.service.GameRouteService;
import dev.shermende.reference.lib.api.MovementPointApiService;
import dev.shermende.reference.lib.api.MovementReasonApiService;
import dev.shermende.support.spring.processor.Step;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
@RequiredArgsConstructor
public class GameCreateProcessorRoutesStep implements Step<GameCreateProcessorCtx, GameCreateProcessorCtx> {

    private final GraphService graphService;
    private final GameRouteService routeService;
    private final MovementPointApiService pointApiService;
    private final MovementReasonApiService reasonApiService;

    @Override
    public GameCreateProcessorCtx execute(
            GameCreateProcessorCtx input
    ) {
        graphService.generateGraph(Math.toIntExact(input.getPointsCount()), input.getEdgeChance())
                .forEach(graphEdge ->
                        routeService.save(GameRoute.builder()
                                .userId(input.getUser().getId())
                                .gameId(input.getGame().getId())
                                .sourcePointId(pointApiService.findPointByScenarioIdAndIndex(input.getScenario().getId(), graphEdge.getSource()).getId())
                                .reasonId(reasonApiService.findPointByScenarioIdAndIndex(input.getScenario().getId(), new Random().nextInt(Math.toIntExact(input.getReasonsCount()))).getId())
                                .targetPointId(pointApiService.findPointByScenarioIdAndIndex(input.getScenario().getId(), graphEdge.getTarget()).getId())
                                .build()));
        return input;
    }

}
