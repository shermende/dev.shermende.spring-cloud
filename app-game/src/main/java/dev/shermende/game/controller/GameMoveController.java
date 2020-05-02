package dev.shermende.game.controller;

import dev.shermende.game.assembler.GameModelAssembler;
import dev.shermende.game.resource.GameMoveResource;
import dev.shermende.game.service.GameService;
import dev.shermende.lib.model.game.GameModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameMoveController {

    private final GameService service;
    private final GameModelAssembler assembler;

    @PutMapping("/move")
    public GameModel move(
        Authentication authentication,
        @Validated @RequestBody GameMoveResource resource
    ) {
        return assembler.toModel(service.move(authentication, resource.getGameId(), resource.getReasonId()));
    }

}