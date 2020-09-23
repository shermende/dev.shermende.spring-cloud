package dev.shermende.lib.security.configuration.jwt;

import dev.shermende.lib.security.model.PrincipalUser;
import dev.shermende.lib.security.model.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;

/**
 * {@link Authentication#getPrincipal()} for JWT session
 */
@Slf4j
@RequiredArgsConstructor
public class JwtUserPrincipalSupportConverter implements Converter<Jwt, UserPrincipal> {

    @NotNull
    @Override
    public PrincipalUser convert(@NotNull Jwt jwt) {
        return PrincipalUser.builder()
            .id(jwt.getClaim("id"))
            .email(jwt.getClaim("user_name"))
            .token(jwt.getTokenValue().toCharArray())
            .build();
    }

}
