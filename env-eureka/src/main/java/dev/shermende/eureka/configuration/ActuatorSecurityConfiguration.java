package dev.shermende.eureka.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * High priority protection
 */
@Slf4j
@Order(0)
@Configuration
@RequiredArgsConstructor
public class ActuatorSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final SecurityProperties securityProperties;

    /**
     * Global password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    /**
     * Local authentication manager settings
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser(securityProperties.getUser().getName())
            .password(passwordEncoder().encode(securityProperties.getUser().getPassword()))
            .authorities(securityProperties.getUser().getRoles().toArray(new String[0]));
    }

    /**
     * Protect actuators only
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/actuator/**").authorizeRequests()
            .anyRequest().authenticated()
            .and().csrf().disable().httpBasic()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;
    }

}