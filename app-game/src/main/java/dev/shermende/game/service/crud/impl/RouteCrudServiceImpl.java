package dev.shermende.game.service.crud.impl;

import dev.shermende.game.db.entity.QRoute;
import dev.shermende.game.db.entity.Route;
import dev.shermende.game.db.repository.RouteRepository;
import dev.shermende.game.service.crud.RouteCrudService;
import dev.shermende.lib.dal.service.AbstractCrudService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RouteCrudServiceImpl extends AbstractCrudService<Route, Long, QRoute> implements RouteCrudService {

    public RouteCrudServiceImpl(
        RouteRepository repository
    ) {
        super(repository);
    }

    @NotNull
    @Override
    public List<Route> getRoutesByGameIdAndSourcePointId(
        @NotNull Long gameId,
        @NotNull Long sourcePointId
    ) {
        return findAll(QRoute.route.gameId.eq(gameId).and(QRoute.route.sourcePointId.eq(sourcePointId)));
    }

}