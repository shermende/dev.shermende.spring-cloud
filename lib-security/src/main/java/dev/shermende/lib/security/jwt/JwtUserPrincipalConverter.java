package dev.shermende.lib.security.jwt;

import dev.shermende.lib.security.model.UserPrincipal;
import dev.shermende.lib.security.model.impl.PrincipalUser;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.jwt.Jwt;

@Slf4j
public class JwtUserPrincipalConverter implements Converter<Jwt, UserPrincipal> {

    /**
     * Force use principal as {@link PrincipalUser}
     */
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
