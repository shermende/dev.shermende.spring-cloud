package dev.shermende.lib.security.jwt;

import dev.shermende.lib.security.model.UserPrincipal;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 *
 */
@Slf4j
@EqualsAndHashCode(callSuper = false, of = {"principal"})
public class JwtWrapperAuthenticationToken extends AbstractAuthenticationToken {

    private final transient UserPrincipal principal;
    private final AbstractAuthenticationToken authentication;

    public JwtWrapperAuthenticationToken(
        UserPrincipal principal,
        AbstractAuthenticationToken authentication
    ) {
        super(authentication.getAuthorities());
        setAuthenticated(true);
        this.principal = principal;
        this.authentication = authentication;
    }

    @Override
    public Object getCredentials() {
        return authentication.getCredentials();
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

}
