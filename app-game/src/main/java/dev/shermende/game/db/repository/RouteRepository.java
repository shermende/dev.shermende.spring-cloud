package dev.shermende.game.db.repository;

import dev.shermende.game.db.entity.QRoute;
import dev.shermende.game.db.entity.Route;
import dev.shermende.lib.dal.db.repository.QueryDslRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "data")
public interface RouteRepository extends QueryDslRepository<Route, Long, QRoute> {

}