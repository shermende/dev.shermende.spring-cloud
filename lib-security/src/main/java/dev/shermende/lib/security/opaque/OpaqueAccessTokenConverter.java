package dev.shermende.lib.security.opaque;

import dev.shermende.lib.security.model.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class OpaqueAccessTokenConverter extends DefaultAccessTokenConverter {

    private final Converter<Map<String, ?>, UserPrincipal> principalConverter;

    /**
     * Force use principal as {@link UserPrincipal}
     */
    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        final OAuth2Authentication authentication = super.extractAuthentication(map);
        return new OAuth2Authentication(
            authentication.getOAuth2Request(),
            new UsernamePasswordAuthenticationToken(principalConverter.convert(map), authentication.getAuthorities())
        );
    }

}
