package dev.shermende.sba.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final SecurityProperties securityProperties;

    /**
     * Local authentication manager settings
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser(securityProperties.getUser().getName())
            .password(passwordEncoder.encode(securityProperties.getUser().getPassword()))
            .authorities(securityProperties.getUser().getRoles().toArray(new String[0]));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/login").permitAll()
            .antMatchers("/assets/**").permitAll()
            .anyRequest().authenticated()
            .and().httpBasic()
            .and().formLogin().loginPage("/login")
            .and().csrf().ignoringAntMatchers("/**")
        ;
    }

}