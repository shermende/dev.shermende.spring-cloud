package dev.shermende.authorization.assembler;

import dev.shermende.authorization.controller.UserController;
import dev.shermende.authorization.db.entity.User;
import dev.shermende.lib.model.authorization.UserModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler extends RepresentationModelAssemblerSupport<User, UserModel> {

    public UserAssembler() {
        super(UserController.class, UserModel.class);
    }

    @NotNull
    @Override
    public UserModel toModel(@NotNull User entity) {
        return UserModel.builder().email(entity.getEmail()).build();
    }

}
