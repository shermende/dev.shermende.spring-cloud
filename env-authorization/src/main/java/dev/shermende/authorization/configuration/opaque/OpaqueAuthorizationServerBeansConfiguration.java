package dev.shermende.authorization.configuration.opaque;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * Opaque authorization server beans
 */
@Slf4j
@Configuration
@Profile({"opaque"})
@RequiredArgsConstructor
public class OpaqueAuthorizationServerBeansConfiguration {

    @Qualifier("dataSource")
    private final DataSource dataSource;
    @Qualifier("passwordEncoder")
    private final PasswordEncoder passwordEncoder;
    @Qualifier("appUserDetailsService")
    private final UserDetailsService userDetailsService;
    @Qualifier("objectPostProcessor")
    private final ObjectPostProcessor<Object> objectObjectPostProcessor;

    /**
     * Opaque authorization server authentication manager
     */
    @Bean
    public AuthenticationManager opaqueAuthorizationServerAuthenticationManager() throws Exception {
        return new AuthenticationManagerBuilder(objectObjectPostProcessor)
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder)
            .and().build();
    }

    /**
     * Opaque authorization server oauth applications storage
     */
    @Bean
    public JdbcClientDetailsService jdbcClientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * Opaque authorization server token store
     */
    @Bean
    public TokenStore opaqueAuthorizationServerTokenStore() {
        return new JdbcTokenStore(dataSource);
    }

}