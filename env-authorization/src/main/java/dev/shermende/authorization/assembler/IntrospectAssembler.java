package dev.shermende.authorization.assembler;

import dev.shermende.authorization.controller.UserController;
import dev.shermende.authorization.db.entity.User;
import dev.shermende.authorization.model.UserModel;
import dev.shermende.authorization.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IntrospectAssembler extends RepresentationModelAssemblerSupport<String, UserModel> {

    private final TokenStore tokenStore;
    private final UserService userService;

    public IntrospectAssembler(
        TokenStore tokenStore,
        UserService userService
    ) {
        super(UserController.class, UserModel.class);
        this.tokenStore = tokenStore;
        this.userService = userService;
    }

    @NotNull
    @Override
    public UserModel toModel(@NotNull String token) {
        final OAuth2Authentication authentication = getAuthentication(prepareToken(token));
        final User user = getUser(token, authentication);
        return UserModel.builder().id(user.getId()).email(user.getEmail()).build();
    }

    @NotNull
    private String prepareToken(@NotNull String token) {
        if (token.startsWith("Bearer "))
            return token.substring("Bearer ".length());
        return token;
    }

    private OAuth2Authentication getAuthentication(@NotNull String token) {
        return Optional.ofNullable(tokenStore.readAuthentication(token))
            .orElseThrow(() -> new UsernameNotFoundException(token));
    }

    private User getUser(@NotNull String token, OAuth2Authentication authentication) {
        return userService.findOneByEmail(authentication.getName())
            .orElseThrow(() -> new UsernameNotFoundException(token));
    }

}