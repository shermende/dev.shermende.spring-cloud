package dev.shermende.authorization.configuration.opaque;

import dev.shermende.authorization.security.opaque.OpaqueDefaultAccessTokenConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.sql.DataSource;

/**
 * Opaque authorization server configuration
 */
@Slf4j
@Configuration
@Profile({"opaque"})
@RequiredArgsConstructor
@EnableAuthorizationServer
public class OpaqueAuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Qualifier("dataSource")
    private final DataSource dataSource;
    @Qualifier("opaqueAuthorizationServerTokenStore")
    private final TokenStore tokenStore;
    @Qualifier("appUserDetailsService")
    private final UserDetailsService userDetailsService;
    @Qualifier("opaqueAuthorizationServerAuthenticationManager")
    private final AuthenticationManager authenticationManager;

    /**
     * Oauth applications storage
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }

    /**
     * Authorization server endpoints access rules
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
            .tokenKeyAccess("isAuthenticated()")
            .checkTokenAccess("isAuthenticated()");
    }

    /**
     * Opaque authorization server settings
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore)
            .userDetailsService(userDetailsService)
            .authenticationManager(authenticationManager)
            .accessTokenConverter(new OpaqueDefaultAccessTokenConverter());

    }

}