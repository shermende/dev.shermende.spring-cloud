package dev.shermende.authorization.security.jwt;

import dev.shermende.authorization.security.ExtendedUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import java.util.Map;

public class JwtDefaultUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

    /**
     * Force use principal as {@link ExtendedUser}
     */
    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
        final Authentication authentication = super.extractAuthentication(map);
        return new UsernamePasswordAuthenticationToken(
            new ExtendedUser(Long.valueOf(map.get("id").toString()), String.valueOf(map.get("user_name")), "", authentication.getAuthorities()),
            authentication.getCredentials(),
            authentication.getAuthorities()
        );
    }

}
