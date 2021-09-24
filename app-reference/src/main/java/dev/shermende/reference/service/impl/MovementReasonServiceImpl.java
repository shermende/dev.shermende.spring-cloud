package dev.shermende.reference.service.impl;

import dev.shermende.lib.dal.service.AbstractCrudService;
import dev.shermende.reference.db.entity.movement.MovementReason;
import dev.shermende.reference.db.entity.movement.QMovementReason;
import dev.shermende.reference.db.repository.movement.MovementReasonRepository;
import dev.shermende.reference.service.MovementReasonService;
import org.springframework.stereotype.Service;

@Service
public class MovementReasonServiceImpl extends AbstractCrudService<MovementReason, Long, QMovementReason>
        implements MovementReasonService {

    public MovementReasonServiceImpl(
            MovementReasonRepository repository
    ) {
        super(repository);
    }

}
