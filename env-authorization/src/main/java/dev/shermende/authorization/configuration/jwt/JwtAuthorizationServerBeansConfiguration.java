package dev.shermende.authorization.configuration.jwt;

import dev.shermende.authorization.security.jwt.JwtDefaultAccessTokenConverter;
import dev.shermende.authorization.security.jwt.JwtDefaultUserAuthenticationConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.validation.annotation.Validated;

import java.net.MalformedURLException;
import java.security.KeyPair;

/**
 * Jwt authorization server beans
 */
@Slf4j
@Configuration
@Profile({"jwt"})
@RequiredArgsConstructor
public class JwtAuthorizationServerBeansConfiguration {

    @Qualifier("passwordEncoder")
    private final PasswordEncoder passwordEncoder;
    @Qualifier("appUserDetailsService")
    private final UserDetailsService userDetailsService;
    @Qualifier("objectPostProcessor")
    private final ObjectPostProcessor<Object> objectObjectPostProcessor;

    /**
     * Jwt authorization server authentication manager
     */
    @Bean
    public AuthenticationManager jwtAuthorizationServerAuthenticationManager() throws Exception {
        return new AuthenticationManagerBuilder(objectObjectPostProcessor)
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder)
            .and().build();
    }

    /**
     * Jwt authorization server token store
     */
    @Bean
    public TokenStore jwtAuthorizationServerTokenStore(
        JwtAccessTokenConverter jwtAccessTokenConverter
    ) {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    /**
     * Jwt authorization server access-token converter
     */
    @Bean
    public JwtAccessTokenConverter jwtAuthorizationServerTokenConverter(
        KeyPair keyPair
    ) {
        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyPair);
        converter.setAccessTokenConverter(new JwtDefaultAccessTokenConverter(new JwtDefaultUserAuthenticationConverter()));
        return converter;
    }

    /**
     *
     */
    @Bean
    public KeyPair keyPair(
        @Qualifier("getKeystoreResource") Resource resource,
        AuthorizationServerProperties properties
    ) {
        return new KeyStoreKeyFactory(resource,
            properties.getJwt().getKeyStorePassword().toCharArray()).getKeyPair(properties.getJwt().getKeyAlias());
    }

    /**
     *
     */
    @Bean
    public Resource getKeystoreResource(
        AuthorizationServerProperties properties
    ) throws MalformedURLException {
        if (!properties.getJwt().getKeyStore().startsWith("resource:"))
            return new FileUrlResource(properties.getJwt().getKeyStore());
        return new ClassPathResource(properties.getJwt().getKeyStore().substring("resource:".length()));
    }

    /**
     *
     */
    @Bean
    @Validated
    @ConfigurationProperties("security.oauth2.authorization")
    public AuthorizationServerProperties authorizationServerProperties() {
        return new AuthorizationServerProperties();
    }

}