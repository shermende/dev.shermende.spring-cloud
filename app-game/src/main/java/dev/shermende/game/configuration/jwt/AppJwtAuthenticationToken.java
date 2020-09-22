package dev.shermende.game.configuration.jwt;

import dev.shermende.game.model.UserModel;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;

@Slf4j
@EqualsAndHashCode(callSuper = false, of = {"user"})
public class AppJwtAuthenticationToken extends AbstractAuthenticationToken {

    private final transient UserModel user;
    private final AbstractAuthenticationToken authentication;

    public AppJwtAuthenticationToken(
        UserModel user,
        AbstractAuthenticationToken authentication
    ) {
        super(authentication.getAuthorities());
        setAuthenticated(true);
        this.user = user;
        this.authentication = authentication;
    }

    @Override
    public Object getCredentials() {
        return authentication.getCredentials();
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

}
