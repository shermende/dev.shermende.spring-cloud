package dev.shermende.authorization.configuration.jwt;

import dev.shermende.lib.security.jwt.JwtAccessTokenConverter;
import dev.shermende.lib.security.jwt.JwtUserPrincipalConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;

/**
 * Jwt resource server beans
 */
@Slf4j
@Configuration
@Profile("jwt")
@RequiredArgsConstructor
public class JwtResourceServerBeansConfiguration {

    private final ObjectPostProcessor<Object> objectObjectPostProcessor;

    /**
     * Jwt resource server authentication manager
     */
    @Bean
    public AuthenticationManager jwtResourceServerAuthenticationManager(
        @Qualifier("jwtResourceServerAuthenticationProvider") AuthenticationProvider authenticationProvider
    ) throws Exception {
        return new AuthenticationManagerBuilder(objectObjectPostProcessor)
            .authenticationProvider(authenticationProvider)
            .build();
    }

    /**
     * Jwt resource server authentication provider
     */
    @Bean
    public AuthenticationProvider jwtResourceServerAuthenticationProvider(
        @Qualifier("jwtResourceServerAccessTokenConverter") JwtAccessTokenConverter tokenConverter,
        @Qualifier("jwtResourceServerDecoder") JwtDecoder jwtDecoder
    ) {
        final JwtAuthenticationProvider jwtAuthenticationProvider = new JwtAuthenticationProvider(jwtDecoder);
        jwtAuthenticationProvider.setJwtAuthenticationConverter(tokenConverter);
        return jwtAuthenticationProvider;
    }

    /**
     * Jwt resource server access-token converter
     */
    @Bean
    public JwtAccessTokenConverter jwtResourceServerAccessTokenConverter() {
        return new JwtAccessTokenConverter(new JwtUserPrincipalConverter(), new JwtAuthenticationConverter());
    }

    /**
     * Jwt resource server jwt-token decoder (public key based)
     */
    @Bean
    public JwtDecoder jwtResourceServerDecoder(
        KeyPair keyPair
    ) {
        return NimbusJwtDecoder.withPublicKey((RSAPublicKey) keyPair.getPublic()).build();
    }

}