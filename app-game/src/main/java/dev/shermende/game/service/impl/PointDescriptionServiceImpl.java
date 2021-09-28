package dev.shermende.game.service.impl;

import dev.shermende.game.db.entity.PointDescription;
import dev.shermende.game.db.entity.QPointDescription;
import dev.shermende.game.db.repository.PointDescriptionRepository;
import dev.shermende.game.service.PointDescriptionService;
import dev.shermende.lib.dal.service.AbstractCrudService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PointDescriptionServiceImpl extends AbstractCrudService<PointDescription, Long, QPointDescription> implements PointDescriptionService {

    public PointDescriptionServiceImpl(
            PointDescriptionRepository repository
    ) {
        super(repository);
    }

    @Override
    public PointDescription create(
            @NotNull Long gameId,
            @NotNull Long pointId,
            @NotNull String intro,
            @NotNull String description
    ) {
        return save(
                PointDescription.builder()
                        .gameId(gameId)
                        .pointId(pointId)
                        .intro(intro)
                        .description(description)
                        .build()
        );
    }

    @Override
    public Optional<PointDescription> findByGameIdAndPointId(
            @NotNull Long gameId,
            @NotNull Long pointId
    ) {
        return findOne(QPointDescription.pointDescription.gameId.eq(gameId)
                .and(QPointDescription.pointDescription.pointId.eq(pointId)));
    }

}
