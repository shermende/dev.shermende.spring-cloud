package dev.shermende.game.controller;

import dev.shermende.game.assembler.GameModelAssembler;
import dev.shermende.game.model.GameModel;
import dev.shermende.game.resource.GameCreateResource;
import dev.shermende.game.service.GameService;
import dev.shermende.game.validator.GameCreateResourceValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameCreateController {

    private final GameService service;
    private final GameModelAssembler assembler;
    private final GameCreateResourceValidator validator;

    @InitBinder("gameCreateResource")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @PostMapping("/create")
    public GameModel create(
        @AuthenticationPrincipal Authentication authentication,
        @Validated @RequestBody GameCreateResource resource
    ) {
        return assembler.toModel(service.create(authentication, resource));
    }

}