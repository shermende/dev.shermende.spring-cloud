package dev.shermende.lib.security.opaque;

import dev.shermende.lib.security.model.UserPrincipal;
import dev.shermende.lib.security.model.impl.PrincipalUser;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;

import java.util.Map;

@Slf4j
public class OpaqueUserPrincipalConverter implements Converter<Map<String, ?>, UserPrincipal> {

    /**
     * Force use principal as {@link UserPrincipal}
     */
    @NotNull
    @Override
    public PrincipalUser convert(@NotNull Map<String, ?> map) {
        return PrincipalUser.builder()
            .id(Long.valueOf(String.valueOf(map.get("id"))))
            .email(String.valueOf(map.get("user_name")))
            .build();
    }

}
