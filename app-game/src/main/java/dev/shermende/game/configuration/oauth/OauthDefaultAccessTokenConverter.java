package dev.shermende.game.configuration.oauth;

import dev.shermende.game.model.UserModel;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;

import java.util.Map;

public class OauthDefaultAccessTokenConverter extends DefaultAccessTokenConverter {

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        final OAuth2Authentication authentication = super.extractAuthentication(map);
        return new OAuth2Authentication(
            authentication.getOAuth2Request(),
            new UsernamePasswordAuthenticationToken(
                UserModel.builder()
                    .id(Long.valueOf(String.valueOf(map.get("id"))))
                    .email(String.valueOf(map.get("user_name")))
                    .build(),
                authentication.getAuthorities()
            )
        );
    }

}
