package dev.shermende.game.service.impl;

import dev.shermende.game.db.entity.Game;
import dev.shermende.game.db.entity.Route;
import dev.shermende.game.model.MovementPointModel;
import dev.shermende.game.model.MovementReasonModel;
import dev.shermende.game.resource.RouteCreateResource;
import dev.shermende.game.service.RouteService;
import dev.shermende.game.service.crud.RouteCrudService;
import dev.shermende.game.service.feign.MovementPointService;
import dev.shermende.game.service.feign.MovementReasonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteCrudService crudService;
    private final MovementPointService movementPointService;
    private final MovementReasonService movementReasonService;

    @Override
    @Transactional
    public Game generateMap(
        @NotNull Game game
    ) {
        final PagedModel<MovementPointModel> points = movementPointService.findAll();
        final MovementReasonModel reason = movementReasonService.findById(1L).orElseThrow(EntityNotFoundException::new);

        points.forEach(movementPointModel -> {
            points.forEach(movementPointModel1 -> {
                if (new Random().nextInt(50) < new Random().nextInt(100)) return;
                create(
                    RouteCreateResource.builder()
                        .gameId(game.getId())
                        .sourcePointId(movementPointModel.getId())
                        .reasonId(reason.getId())
                        .targetPointId(movementPointModel1.getId())
                        .build()
                );
            });
        });
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