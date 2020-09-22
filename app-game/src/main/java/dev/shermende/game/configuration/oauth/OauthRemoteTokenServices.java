package dev.shermende.game.configuration.oauth;

import dev.shermende.game.model.UserModel;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

public class OauthRemoteTokenServices extends RemoteTokenServices {

    @Override
    public OAuth2Authentication loadAuthentication(String accessToken) {
        final OAuth2Authentication authentication = super.loadAuthentication(accessToken);
        final UserModel model = (UserModel) authentication.getPrincipal();
        return new OAuth2Authentication(
            authentication.getOAuth2Request(),
            new UsernamePasswordAuthenticationToken(
                model.setToken(accessToken),
                authentication.getCredentials(),
                authentication.getAuthorities()
            )
        );
    }
}
