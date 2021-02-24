package dev.shermende.game.processor.steps;

import dev.shermende.game.model.MovementReasonModel;
import dev.shermende.game.processor.RouteGenerateProcessorCtx;
import dev.shermende.game.resource.RouteCreateResource;
import dev.shermende.game.service.RouteService;
import dev.shermende.game.service.feign.MovementReasonService;
import dev.shermende.support.spring.processor.Step;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class RouteGenerateProcessorQuestStep implements Step<RouteGenerateProcessorCtx, RouteGenerateProcessorCtx> {

    private final RouteService routeService;
    private final MovementReasonService movementReasonService;

    @NotNull
    @Override
    public RouteGenerateProcessorCtx execute(
        @NotNull RouteGenerateProcessorCtx ctx
    ) {
        if (Objects.isNull(ctx.getSource().getQuests()) || ctx.getSource().getQuests().isEmpty()) return ctx;

        final MovementReasonModel reason = movementReasonService.findById(2L).orElseThrow(EntityNotFoundException::new);
        routeService.create(
            RouteCreateResource.builder()
                .gameId(ctx.getGame().getId())
                .sourcePointId(ctx.getSource().getId())
                .reasonId(reason.getId())
                .targetPointId(ctx.getSource().getId())
                .build()
        );
        return ctx;
    }

}