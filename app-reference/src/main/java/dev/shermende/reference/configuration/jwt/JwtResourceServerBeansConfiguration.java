package dev.shermende.reference.configuration.jwt;

import dev.shermende.lib.security.jwt.JwtAccessTokenConverter;
import dev.shermende.lib.security.jwt.JwtUserPrincipalConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;

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
        OAuth2ResourceServerProperties properties
    ) throws Exception {
        // authentication provider
        final JwtAuthenticationProvider jwtAuthenticationProvider =
            new JwtAuthenticationProvider(NimbusJwtDecoder.withJwkSetUri(properties.getJwt().getJwkSetUri()).build());
        jwtAuthenticationProvider.setJwtAuthenticationConverter(
            new JwtAccessTokenConverter(new JwtUserPrincipalConverter(), new JwtAuthenticationConverter()));
        // authentication manager
        return new AuthenticationManagerBuilder(objectObjectPostProcessor)
            .authenticationProvider(jwtAuthenticationProvider)
            .build();
    }

}