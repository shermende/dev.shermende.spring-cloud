package dev.shermende.sba;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/instances/**").anonymous() // management port
            .antMatchers("/actuator/**").anonymous() // management port
            .antMatchers("/assets/**").anonymous()
            .antMatchers("/login").anonymous()
            .anyRequest().authenticated()
            .and().formLogin().loginPage("/login").and().httpBasic()
            .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            .ignoringAntMatchers("/instances", "/actuator/**")
        ;
    }

}
