package dev.shermende.lib.secure.configuration.ouath;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.validation.annotation.Validated;

@Configuration
public class OauthSecurityConfiguration {

    /**
     * bean of oauth properties
     */
    @Bean
    @Validated
    @ConfigurationProperties("x-oauth")
    public OAuthProperties oAuthProperties() {
        return new OAuthProperties();
    }

    /**
     * oauth token service
     * provide {@link org.springframework.security.core.Authentication} by token
     */
    @Bean
    public ResourceServerTokenServices tokenService(
        OAuthProperties properties
    ) {
        final OauthRemoteTokenServices tokenServices = new OauthRemoteTokenServices();
        tokenServices.setClientId(properties.getClient());
        tokenServices.setClientSecret(properties.getSecret());
        tokenServices.setCheckTokenEndpointUrl(properties.getUrl());
        tokenServices.setAccessTokenConverter(new OauthAccessTokenConverter(new OauthUserPrincipalConverter()));
        return tokenServices;
    }

}