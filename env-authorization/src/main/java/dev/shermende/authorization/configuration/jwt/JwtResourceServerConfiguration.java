package dev.shermende.authorization.configuration.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Jwt resource server configuration
 */
@Slf4j
@Configuration
@Profile("jwt")
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JwtResourceServerConfiguration extends WebSecurityConfigurerAdapter {

    @Qualifier("jwtResourceServerAuthenticationManager")
    private final AuthenticationManager authenticationManager;

    /**
     * Jwt resource server security exceptions
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
            "/.well-known/jwks.json",
            "/registration/**",
            "/error/**"
        );
    }

    /**
     * Jwt resource server settings
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .anyRequest().authenticated()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().csrf().disable().httpBasic().disable().cors()
            .and().oauth2ResourceServer().jwt().authenticationManager(authenticationManager);
    }

}