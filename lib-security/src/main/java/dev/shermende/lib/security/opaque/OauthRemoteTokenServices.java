package dev.shermende.lib.security.opaque;

import dev.shermende.lib.security.model.UserPrincipal;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

public class OauthRemoteTokenServices extends RemoteTokenServices {

    /**
     * Force attach token to principal
     */
    @Override
    public OAuth2Authentication loadAuthentication(String accessToken) {
        final OAuth2Authentication authentication = super.loadAuthentication(accessToken);
        final UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        return new OAuth2Authentication(
            authentication.getOAuth2Request(),
            new UsernamePasswordAuthenticationToken(
                principal.attachToken(accessToken.toCharArray()),
                authentication.getCredentials(),
                authentication.getAuthorities()
            )
        );
    }
}
