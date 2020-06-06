package dev.shermende.reference.service.impl;

import dev.shermende.lib.db.service.AbstractCrudService;
import dev.shermende.reference.db.entity.movement.MovementScenario;
import dev.shermende.reference.db.entity.movement.QMovementScenario;
import dev.shermende.reference.db.repository.movement.MovementScenarioRepository;
import dev.shermende.reference.service.MovementScenarioService;
import org.springframework.stereotype.Service;

@Service
public class MovementScenarioServiceImpl extends AbstractCrudService<MovementScenario, Long, QMovementScenario>
    implements MovementScenarioService {

    public MovementScenarioServiceImpl(
        MovementScenarioRepository repository
    ) {
        super(repository);
    }

}
