package dev.shermende.lib.secure.ouath;

import dev.shermende.lib.secure.model.PrincipalUser;
import dev.shermende.lib.secure.model.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.Authentication;

import java.util.Map;

/**
 * {@link Authentication#getPrincipal()} for OAUTH session
 */
@Slf4j
@RequiredArgsConstructor
public class OauthUserPrincipalConverter implements Converter<Map<String, ?>, UserPrincipal> {

    @NotNull
    @Override
    public PrincipalUser convert(@NotNull Map<String, ?> map) {
        return PrincipalUser.builder()
            .id(Long.valueOf(String.valueOf(map.get("id"))))
            .email(String.valueOf(map.get("user_name")))
            .build();
    }

}
