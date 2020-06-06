package dev.shermende.authorization.controller;

import dev.shermende.authorization.assembler.IntrospectAssembler;
import dev.shermende.authorization.assembler.UserAssembler;
import dev.shermende.authorization.model.UserModel;
import dev.shermende.authorization.resource.UserResource;
import dev.shermende.authorization.service.UserService;
import dev.shermende.authorization.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final UserValidator validator;
    private final UserAssembler assembler;
    private final IntrospectAssembler introspectAssembler;

    @InitBinder("userResource")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @PostMapping("/introspect")
    public UserModel introspect(
        @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ) {
        return introspectAssembler.toModel(token);
    }

    @PostMapping("/registration")
    public UserModel registration(
        @Validated @RequestBody UserResource resource
    ) {
        return assembler.toModel(service.registration(resource));
    }

}
