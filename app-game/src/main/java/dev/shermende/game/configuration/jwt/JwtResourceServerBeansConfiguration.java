package dev.shermende.game.configuration.jwt;

import dev.shermende.lib.security.jwt.JwtAccessTokenConverter;
import dev.shermende.lib.security.jwt.JwtUserPrincipalConverter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;

import java.net.URI;

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
        @Qualifier("jwkSetUri") String uri
    ) throws Exception {
        // authentication provider
        final JwtAuthenticationProvider jwtAuthenticationProvider =
            new JwtAuthenticationProvider(NimbusJwtDecoder.withJwkSetUri(uri).build());
        jwtAuthenticationProvider.setJwtAuthenticationConverter(
            new JwtAccessTokenConverter(new JwtUserPrincipalConverter(), new JwtAuthenticationConverter()));
        // authentication manager
        return new AuthenticationManagerBuilder(objectObjectPostProcessor)
            .authenticationProvider(jwtAuthenticationProvider)
            .build();
    }

    /**
     *
     */
    @Bean
    @SneakyThrows
    public String jwkSetUri(
        DiscoveryClient discoveryClient,
        OAuth2ResourceServerProperties properties
    ) {
        final URI uri = new URI(properties.getJwt().getJwkSetUri());
        final String host = uri.getHost();
        final String path = uri.getPath();
        return discoveryClient.getInstances(host).stream().findAny()
            .map(serviceInstance -> String.format("%s%s", serviceInstance.getUri().toString(), path)).orElse(properties.getJwt().getJwkSetUri());
    }

}