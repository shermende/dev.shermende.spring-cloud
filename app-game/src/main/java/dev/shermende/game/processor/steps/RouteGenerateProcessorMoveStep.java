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
import java.util.Random;

@Slf4j
@Component
@RequiredArgsConstructor
public class RouteGenerateProcessorMoveStep implements Step<RouteGenerateProcessorCtx, RouteGenerateProcessorCtx> {

    private final RouteService routeService;
    private final MovementReasonService movementReasonService;

    @NotNull
    @Override
    public RouteGenerateProcessorCtx execute(
        @NotNull RouteGenerateProcessorCtx ctx
    ) {
        if (50 < new Random().nextInt(100)) return ctx;

        final MovementReasonModel reason = movementReasonService.findById(1L).orElseThrow(EntityNotFoundException::new);
        routeService.create(
            RouteCreateResource.builder()
                .gameId(ctx.getGame().getId())
                .sourcePointId(ctx.getSource().getId())
                .reasonId(reason.getId())
                .targetPointId(ctx.getTarget().getId())
                .build()
        );
        return ctx;
    }

}