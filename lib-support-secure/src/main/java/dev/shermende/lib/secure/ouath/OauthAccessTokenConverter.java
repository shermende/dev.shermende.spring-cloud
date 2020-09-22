package dev.shermende.lib.secure.ouath;

import dev.shermende.lib.secure.model.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;

import java.util.Map;

/**
 * {@link org.springframework.security.core.Authentication} for OAUTH session
 */
@RequiredArgsConstructor
public class OauthAccessTokenConverter extends DefaultAccessTokenConverter {

    private final Converter<Map<String, ?>, UserPrincipal> principalConverter;

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        final OAuth2Authentication authentication = super.extractAuthentication(map);
        return new OAuth2Authentication(
            authentication.getOAuth2Request(),
            new UsernamePasswordAuthenticationToken(principalConverter.convert(map), authentication.getAuthorities())
        );
    }

}
