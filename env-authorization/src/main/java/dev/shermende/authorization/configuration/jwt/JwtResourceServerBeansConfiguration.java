package dev.shermende.authorization.configuration.jwt;

import dev.shermende.lib.security.jwt.JwtAccessTokenConverter;
import dev.shermende.lib.security.jwt.JwtUserPrincipalConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
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
        KeyPair keyPair
    ) throws Exception {
        // authentication provider
        final JwtAuthenticationProvider jwtAuthenticationProvider =
            new JwtAuthenticationProvider(NimbusJwtDecoder.withPublicKey((RSAPublicKey) keyPair.getPublic()).build());
        jwtAuthenticationProvider.setJwtAuthenticationConverter(
            new JwtAccessTokenConverter(new JwtUserPrincipalConverter()));
        // authentication manager
        return new AuthenticationManagerBuilder(objectObjectPostProcessor)
            .authenticationProvider(jwtAuthenticationProvider)
            .build();
    }

}