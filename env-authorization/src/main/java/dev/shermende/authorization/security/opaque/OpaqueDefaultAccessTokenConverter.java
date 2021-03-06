package dev.shermende.authorization.security.opaque;

import dev.shermende.authorization.security.ExtendedUser;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

public class OpaqueDefaultAccessTokenConverter extends DefaultAccessTokenConverter {

    /**
     * Force add id field
     */
    @Override
    public Map<String, ?> convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        // здесь можно добавить данные в jwt::payload
        final ExtendedUser extendedUser = (ExtendedUser) authentication.getPrincipal();
        final Map<String, Object> payload = new HashMap<>(super.convertAccessToken(token, authentication));
        payload.put("id", extendedUser.getId());
        return payload;
    }

}
