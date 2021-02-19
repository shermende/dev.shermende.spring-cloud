package dev.shermende.game.configuration.opaque;

import dev.shermende.lib.security.opaque.OauthRemoteTokenServices;
import dev.shermende.lib.security.opaque.OpaqueAccessTokenConverter;
import dev.shermende.lib.security.opaque.OpaqueUserPrincipalConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;

/**
 * Opaque resource server beans
 */
@Slf4j
@Configuration
@Profile("opaque")
@RequiredArgsConstructor
public class OpaqueResourceServerBeansConfiguration {

    /**
     * Opaque resource server authentication manager
     */
    @Bean
    public AuthenticationManager opaqueResourceServerAuthenticationManager(
        OAuth2ResourceServerProperties properties
    ) {
        // token service
        final OauthRemoteTokenServices tokenServices = new OauthRemoteTokenServices();
        tokenServices.setClientId(properties.getOpaquetoken().getClientId());
        tokenServices.setClientSecret(properties.getOpaquetoken().getClientSecret());
        tokenServices.setCheckTokenEndpointUrl(properties.getOpaquetoken().getIntrospectionUri());
        tokenServices.setAccessTokenConverter(new OpaqueAccessTokenConverter(new OpaqueUserPrincipalConverter()));
        // authentication manager
        final OAuth2AuthenticationManager manager = new OAuth2AuthenticationManager();
        manager.setTokenServices(tokenServices);
        return manager;
    }

}