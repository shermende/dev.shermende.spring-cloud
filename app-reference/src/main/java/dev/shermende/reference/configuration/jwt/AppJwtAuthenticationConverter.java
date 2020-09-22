package dev.shermende.reference.configuration.jwt;

import dev.shermende.reference.model.UserModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class AppJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final Converter<Jwt, AbstractAuthenticationToken> converter;

    @NotNull
    @Override
    public AbstractAuthenticationToken convert(@NotNull Jwt jwt) {
        final AbstractAuthenticationToken token = Objects.requireNonNull(converter.convert(jwt));
        return new AppJwtAuthenticationToken(
            UserModel.builder()
                .id(jwt.getClaim("id"))
                .email(jwt.getClaim("user_name"))
                .token(jwt.getTokenValue())
                .build(),
            token
        );
    }

}
