package dev.shermende.game.configuration.jwt;

import dev.shermende.lib.secure.util.RSA;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPublicKey;

@Configuration
@Profile({"jwt"})
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JwtSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public RSAPublicKey rsaPublicKey(
        @Value("classpath:jwt/public.pem") Resource publicKeyFile
    ) throws IOException, GeneralSecurityException {
        return RSA.getPublicKey(publicKeyFile.getFile().getAbsolutePath());
    }

    @Bean
    public JwtDecoder jwtDecoder(RSAPublicKey rsaPublicKey) {
        return NimbusJwtDecoder.withPublicKey(rsaPublicKey).build();
    }

    @Bean
    public AppJwtAuthenticationConverter appJwtAuthenticationConverter() {
        return new AppJwtAuthenticationConverter(new JwtAuthenticationConverter());
    }

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
            .jwtAuthenticationConverter(jwt -> appJwtAuthenticationConverter().convert(jwt))
        ;
    }

}