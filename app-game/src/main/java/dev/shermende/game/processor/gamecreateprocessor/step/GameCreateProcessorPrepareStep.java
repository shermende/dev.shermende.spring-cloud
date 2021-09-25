package dev.shermende.game.processor.gamecreateprocessor.step;

import dev.shermende.game.processor.gamecreateprocessor.GameCreateProcessorCtx;
import dev.shermende.lib.security.model.impl.PrincipalUser;
import dev.shermende.reference.lib.api.MovementPointApiService;
import dev.shermende.reference.lib.api.MovementReasonApiService;
import dev.shermende.reference.lib.api.MovementScenarioApiService;
import dev.shermende.support.spring.processor.Step;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GameCreateProcessorPrepareStep implements Step<GameCreateProcessorCtx, GameCreateProcessorCtx> {

    private final MovementScenarioApiService scenarioApiService;
    private final MovementReasonApiService reasonApiService;
    private final MovementPointApiService pointApiService;

    @Override
    public GameCreateProcessorCtx execute(
            GameCreateProcessorCtx input
    ) {
        return input
                .setEdgeChance(25)
                .setUser((PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .setScenario(scenarioApiService.findById(input.getResource().getScenarioId()))
                .setPointsCount(pointApiService.countPointsByScenario(input.getResource().getScenarioId()))
                .setReasonsCount(reasonApiService.countReasonsByScenario(input.getResource().getScenarioId()));
    }

}
