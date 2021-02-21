package dev.shermende.reference.db.repository.movement;

import dev.shermende.lib.dal.db.repository.QueryDslRepository;
import dev.shermende.reference.db.entity.movement.MovementScenario;
import dev.shermende.reference.db.entity.movement.QMovementScenario;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "data")
public interface MovementScenarioRepository extends QueryDslRepository<MovementScenario, Long, QMovementScenario> {

}