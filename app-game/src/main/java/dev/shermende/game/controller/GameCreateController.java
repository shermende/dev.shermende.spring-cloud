package dev.shermende.game.controller;

import dev.shermende.game.assembler.GameModelAssembler;
import dev.shermende.game.resource.GameCreateResource;
import dev.shermende.game.service.GameService;
import dev.shermende.lib.model.game.GameModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameCreateController {

    private final GameService service;
    private final GameModelAssembler assembler;

    @PostMapping("/create")
    public GameModel create(
        Authentication authentication,
        @Validated @RequestBody GameCreateResource resource
    ) {
        return assembler.toModel(service.create(authentication, resource.getScenarioId()));
    }

}