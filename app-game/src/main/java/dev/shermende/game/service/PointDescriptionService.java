package dev.shermende.game.service;

import dev.shermende.game.db.entity.PointDescription;
import dev.shermende.lib.dal.service.CrudService;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface PointDescriptionService extends CrudService<PointDescription, Long> {

    PointDescription create(
            @NotNull Long gameId,
            @NotNull Long pointId,
            @NotNull String intro,
            @NotNull String description
    );

    Optional<PointDescription> findByGameIdAndPointId(
            @NotNull Long gameId,
            @NotNull Long pointId
    );

}
