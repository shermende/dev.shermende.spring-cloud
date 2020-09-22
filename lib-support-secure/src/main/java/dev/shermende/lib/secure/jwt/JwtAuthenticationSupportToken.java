package dev.shermende.lib.secure.jwt;

import dev.shermende.lib.secure.model.UserPrincipal;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * JWT holder {@link org.springframework.security.core.Authentication}
 */
@Slf4j
@EqualsAndHashCode(callSuper = false, of = {"principal"})
public class JwtAuthenticationSupportToken extends AbstractAuthenticationToken {

    private final transient UserPrincipal principal;
    private final AbstractAuthenticationToken authentication;

    public JwtAuthenticationSupportToken(
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
