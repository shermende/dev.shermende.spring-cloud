package dev.shermende.game.db.repository;

import dev.shermende.game.db.entity.Game;
import dev.shermende.game.db.entity.QGame;
import dev.shermende.lib.dal.db.repository.QueryDslRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "data")
public interface GameRepository extends QueryDslRepository<Game, Long, QGame> {

}