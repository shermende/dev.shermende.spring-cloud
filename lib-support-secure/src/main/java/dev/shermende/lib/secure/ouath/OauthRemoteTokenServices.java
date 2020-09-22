package dev.shermende.lib.secure.ouath;

import dev.shermende.lib.secure.model.UserPrincipal;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/**
 * "Proxy/Decorator" for {@link RemoteTokenServices}
 * Only attach token to {@link Authentication#getPrincipal()}
 */
public class OauthRemoteTokenServices extends RemoteTokenServices {

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
