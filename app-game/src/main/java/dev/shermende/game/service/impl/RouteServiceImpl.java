package dev.shermende.game.service.impl;

import dev.shermende.game.db.entity.Game;
import dev.shermende.game.db.entity.Route;
import dev.shermende.game.model.MovementPointModel;
import dev.shermende.game.processor.RouteGenerateProcessor;
import dev.shermende.game.processor.RouteGenerateProcessorCtx;
import dev.shermende.game.resource.RouteCreateResource;
import dev.shermende.game.service.RouteService;
import dev.shermende.game.service.crud.RouteCrudService;
import dev.shermende.game.service.feign.MovementPointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteCrudService crudService;
    private final MovementPointService movementPointService;
    private final RouteGenerateProcessor routeGenerateProcessor;

    @Override
    @Transactional
    public Game generateMap(
        @NotNull Game game
    ) {
        final PagedModel<MovementPointModel> points = movementPointService.findAll();

        points.forEach(source ->
            points.forEach(target ->
                routeGenerateProcessor.execute(RouteGenerateProcessorCtx.builder().game(game).source(source).target(target).build())));
        log.debug("[Map] [generated] [{}]", game);
        return game;
    }

    @NotNull
    @Override
    public Route create(
        @NotNull RouteCreateResource resource
    ) {
        final Route entity = crudService.save(Route.builder()
            .gameId(resource.getGameId())
            .sourcePointId(resource.getSourcePointId())
            .reasonId(resource.getReasonId())
            .targetPointId(resource.getTargetPointId())
            .build());
        log.debug("[Route] [created] [{}]", entity);
        return entity;
    }


}