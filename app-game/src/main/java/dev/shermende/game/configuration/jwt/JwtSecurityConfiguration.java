package dev.shermende.game.configuration.jwt;

import dev.shermende.lib.secure.configuration.jwt.JwtAuthenticationSupportConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@Profile({"jwt"})
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(dev.shermende.lib.secure.configuration.jwt.JwtSecurityConfiguration.class)
public class JwtSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationSupportConverter supportConverter;

    /**
     * specify AuthenticationManager bean
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/instances/**").permitAll()  // management port
            .antMatchers("/actuator/**").permitAll()  // management port
            .anyRequest().authenticated()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().cors().and().anonymous().disable().httpBasic().disable().csrf().disable()
            .oauth2ResourceServer().jwt()
            .jwtAuthenticationConverter(supportConverter)
        ;
    }

}