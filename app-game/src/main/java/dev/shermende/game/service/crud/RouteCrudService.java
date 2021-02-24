package dev.shermende.game.service.crud;

import dev.shermende.game.db.entity.Route;
import dev.shermende.lib.dal.service.CrudService;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface RouteCrudService extends CrudService<Route, Long> {

    @NotNull
    List<Route> getRoutesByGameIdAndSourcePointId(
        @NotNull Long gameId,
        @NotNull Long sourcePointId
    );

}
