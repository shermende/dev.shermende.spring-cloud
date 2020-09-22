package dev.shermende.lib.secure.configuration.jwt;

import dev.shermende.lib.secure.model.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Objects;

/**
 * {@link org.springframework.security.core.Authentication} for JWT session
 */
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationSupportConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final Converter<Jwt, UserPrincipal> principalConverter;
    private final Converter<Jwt, AbstractAuthenticationToken> authenticationConverter;

    @NotNull
    @Override
    public AbstractAuthenticationToken convert(@NotNull Jwt jwt) {
        final AbstractAuthenticationToken token = Objects.requireNonNull(authenticationConverter.convert(jwt));
        return new JwtAuthenticationSupportToken(principalConverter.convert(jwt), token);
    }

}
