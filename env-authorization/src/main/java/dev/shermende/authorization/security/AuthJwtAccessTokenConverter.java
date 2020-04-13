package dev.shermende.authorization.security;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Map;

public class AuthJwtAccessTokenConverter extends JwtAccessTokenConverter {

    public AuthJwtAccessTokenConverter() {
        this.setAccessTokenConverter(new AuthDefaultAccessTokenConverter());
    }

    @Override
    public Map<String, ?> convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        return getAccessTokenConverter().convertAccessToken(token, authentication);
    }

}
