package dev.shermende.reference.service.impl;

import dev.shermende.lib.dal.service.AbstractCrudService;
import dev.shermende.reference.db.entity.movement.MovementPoint;
import dev.shermende.reference.db.entity.movement.QMovementPoint;
import dev.shermende.reference.db.repository.movement.MovementPointRepository;
import dev.shermende.reference.service.MovementPointService;
import org.springframework.stereotype.Service;

@Service
public class MovementPointServiceImpl extends AbstractCrudService<MovementPoint, Long, QMovementPoint>
        implements MovementPointService {

    public MovementPointServiceImpl(
            MovementPointRepository repository
    ) {
        super(repository);
    }

}
