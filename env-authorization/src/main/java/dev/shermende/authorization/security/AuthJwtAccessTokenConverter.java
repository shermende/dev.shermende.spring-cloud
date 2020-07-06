package dev.shermende.authorization.security;

import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.security.KeyPair;

public class AuthJwtAccessTokenConverter extends JwtAccessTokenConverter {

    public AuthJwtAccessTokenConverter(KeyPair keyPair, AccessTokenConverter accessTokenConverter) {
        this.setKeyPair(keyPair);
        this.setAccessTokenConverter(accessTokenConverter);
    }

}
