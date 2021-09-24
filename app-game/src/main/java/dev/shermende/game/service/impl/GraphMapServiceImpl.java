package dev.shermende.game.service.impl;

import dev.shermende.game.db.entity.GraphMap;
import dev.shermende.game.db.entity.QGraphMap;
import dev.shermende.game.db.repository.GraphMapRepository;
import dev.shermende.game.service.GraphMapService;
import dev.shermende.lib.dal.service.AbstractCrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GraphMapServiceImpl extends AbstractCrudService<GraphMap, Long, QGraphMap>
        implements GraphMapService {

    private final GraphMapRepository repository;

    public GraphMapServiceImpl(
            GraphMapRepository repository
    ) {
        super(repository);
        this.repository = repository;
    }

}
