package dev.shermende.game.service.crud.impl;

import com.querydsl.core.types.Predicate;
import dev.shermende.game.db.entity.Game;
import dev.shermende.game.db.entity.QGame;
import dev.shermende.game.db.repository.GameRepository;
import dev.shermende.game.service.crud.GameCrudService;
import dev.shermende.lib.dal.service.AbstractCrudService;
import dev.shermende.lib.security.model.impl.PrincipalUser;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GameCrudServiceImpl extends AbstractCrudService<Game, Long, QGame> implements GameCrudService {

    public GameCrudServiceImpl(
        GameRepository repository
    ) {
        super(repository);
    }

    @Override
    public @NotNull Page<Game> findAllByAuthentication(
        @NotNull Authentication authentication,
        @Nullable Predicate predicate,
        @NotNull Pageable pageable
    ) {
        final PrincipalUser auth = (PrincipalUser) authentication.getPrincipal();
        return findAll(QGame.game.userId.eq(auth.getId()).and(predicate), pageable);
    }

}