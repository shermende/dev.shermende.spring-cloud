package dev.shermende.game.service.impl;

import dev.shermende.game.db.entity.GameRoute;
import dev.shermende.game.db.entity.QGameRoute;
import dev.shermende.game.db.repository.GameRouteRepository;
import dev.shermende.game.service.GameRouteService;
import dev.shermende.lib.dal.service.AbstractCrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GameRouteServiceImpl extends AbstractCrudService<GameRoute, Long, QGameRoute>
        implements GameRouteService {

    private final GameRouteRepository repository;

    public GameRouteServiceImpl(
            GameRouteRepository repository
    ) {
        super(repository);
        this.repository = repository;
    }

}
