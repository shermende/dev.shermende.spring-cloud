package dev.shermende.authorization.validator;

import dev.shermende.authorization.resource.UserResource;
import dev.shermende.authorization.service.UserService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {

    private final UserService service;

    @Override
    public boolean supports(@NotNull Class<?> aClass) {
        return UserResource.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(@NotNull Object o, @NotNull Errors errors) {
        final UserResource resource = (UserResource) o;

        if (service.findOneByEmail(resource.getEmail()).isPresent())
            errors.rejectValue("email", "already-exist");

    }

}