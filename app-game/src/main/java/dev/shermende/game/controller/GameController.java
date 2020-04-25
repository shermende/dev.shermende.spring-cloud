package dev.shermende.game.controller;

import com.querydsl.core.types.Predicate;
import dev.shermende.game.assembler.GameModelAssembler;
import dev.shermende.game.db.entity.Game;
import dev.shermende.game.service.GameService;
import dev.shermende.lib.model.game.GameModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService service;
    private final GameModelAssembler assembler;

    @GetMapping
    public PagedModel<GameModel> findAll(
        Authentication authentication,
        @PageableDefault Pageable pageable,
        @QuerydslPredicate(root = Game.class) Predicate predicate,
        PagedResourcesAssembler<Game> pagedResourcesAssembler
    ) {
        return pagedResourcesAssembler.toModel(service.findAll(authentication, predicate, pageable), assembler);
    }

}