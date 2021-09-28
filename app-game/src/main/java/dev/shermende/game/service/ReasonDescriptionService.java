package dev.shermende.game.service;

import dev.shermende.game.db.entity.ReasonDescription;
import dev.shermende.lib.dal.service.CrudService;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface ReasonDescriptionService extends CrudService<ReasonDescription, Long> {

    ReasonDescription create(
            @NotNull Long gameId,
            @NotNull Long reasonId,
            @NotNull String intro,
            @NotNull String description
    );

    Optional<ReasonDescription> findByGameIdAndPointId(
            @NotNull Long gameId,
            @NotNull Long reasonId
    );

}
