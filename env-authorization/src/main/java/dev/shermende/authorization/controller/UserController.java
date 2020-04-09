package dev.shermende.authorization.controller;

import dev.shermende.authorization.assembler.UserAssembler;
import dev.shermende.authorization.model.UserModel;
import dev.shermende.authorization.resource.UserResource;
import dev.shermende.authorization.service.UserService;
import dev.shermende.authorization.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final UserValidator validator;
    private final UserAssembler assembler;

    @InitBinder("userResource")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @PostMapping("/registration")
    public UserModel registration(
        @Validated @RequestBody UserResource resource
    ) {
        return assembler.toModel(service.registration(resource));
    }

}
