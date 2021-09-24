package dev.shermende.reference.service;

import dev.shermende.lib.dal.service.CrudService;
import dev.shermende.reference.db.entity.movement.MovementPoint;
import org.jetbrains.annotations.NotNull;

public interface MovementPointService extends CrudService<MovementPoint, Long> {

    MovementPoint findScenarioPointByIndex(
            @NotNull Long scenarioId,
            int index
    );

    Long countByScenarioId(
            @NotNull Long scenarioId
    );


}
