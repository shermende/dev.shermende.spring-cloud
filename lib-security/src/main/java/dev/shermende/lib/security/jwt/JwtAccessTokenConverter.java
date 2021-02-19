package dev.shermende.lib.security.jwt;

import dev.shermende.lib.security.model.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class JwtAccessTokenConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final Converter<Jwt, UserPrincipal> principalConverter;
    private final Converter<Jwt, AbstractAuthenticationToken> authenticationConverter;

    /**
     * Force use principal as {@link UserPrincipal}
     */
    @NotNull
    @Override
    public AbstractAuthenticationToken convert(@NotNull Jwt jwt) {
        final AbstractAuthenticationToken token = Objects.requireNonNull(authenticationConverter.convert(jwt));
        return new JwtWrapperAuthenticationToken(principalConverter.convert(jwt), token);
    }

}
