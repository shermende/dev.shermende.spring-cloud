package dev.shermende.game.service;

import com.querydsl.core.types.Predicate;
import dev.shermende.game.db.entity.Game;
import dev.shermende.lib.db.service.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface GameService extends CrudService<Game, Long> {

    Page<Game> findAll(Authentication authentication, Predicate predicate, Pageable pageable);

    Game create(Authentication authentication, Long scenarioId);

    Game move(Authentication authentication, Long gameId, Long reasonId);

}
