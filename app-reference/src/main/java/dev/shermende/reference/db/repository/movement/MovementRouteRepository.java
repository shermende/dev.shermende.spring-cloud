package dev.shermende.reference.db.repository.movement;

import dev.shermende.lib.dal.db.repository.QueryDslRepository;
import dev.shermende.reference.db.entity.movement.MovementRoute;
import dev.shermende.reference.db.entity.movement.QMovementRoute;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "data")
public interface MovementRouteRepository extends QueryDslRepository<MovementRoute, Long, QMovementRoute> {

}