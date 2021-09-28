package dev.shermende.game.service.impl;

import dev.shermende.game.db.entity.QReasonDescription;
import dev.shermende.game.db.entity.ReasonDescription;
import dev.shermende.game.db.repository.ReasonDescriptionRepository;
import dev.shermende.game.service.ReasonDescriptionService;
import dev.shermende.lib.dal.service.AbstractCrudService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReasonDescriptionServiceImpl extends AbstractCrudService<ReasonDescription, Long, QReasonDescription> implements ReasonDescriptionService {

    public ReasonDescriptionServiceImpl(
            ReasonDescriptionRepository repository
    ) {
        super(repository);
    }

    @Override
    public ReasonDescription create(
            @NotNull Long gameId,
            @NotNull Long reasonId,
            @NotNull String intro,
            @NotNull String description
    ) {
        return save(
                ReasonDescription.builder()
                        .gameId(gameId)
                        .reasonId(reasonId)
                        .intro(intro)
                        .description(description)
                        .build()
        );
    }

    @Override
    public Optional<ReasonDescription> findByGameIdAndPointId(
            @NotNull Long gameId,
            @NotNull Long reasonId
    ) {
        return findOne(QReasonDescription.reasonDescription.gameId.eq(gameId)
                .and(QReasonDescription.reasonDescription.reasonId.eq(reasonId)));
    }

}
