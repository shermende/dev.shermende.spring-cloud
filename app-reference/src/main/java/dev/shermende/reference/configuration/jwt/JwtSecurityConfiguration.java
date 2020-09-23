package dev.shermende.reference.configuration.jwt;

import dev.shermende.lib.security.configuration.jwt.JwtAuthenticationSupportConverter;
import dev.shermende.lib.security.configuration.jwt.JwtProperties;
import dev.shermende.lib.security.configuration.jwt.JwtUserPrincipalSupportConverter;
import dev.shermende.lib.security.util.RSA;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPublicKey;

@Configuration
@Profile({"jwt"})
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JwtSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * bean of jwt properties
     */
    @Bean
    @Validated
    @ConfigurationProperties("x-jwt")
    public JwtProperties jwtProperties() {
        return new JwtProperties();
    }

    /**
     * load public key
     */
    @Bean
    public RSAPublicKey rsaPublicKey() throws GeneralSecurityException, IOException {
        return RSA.getPublicKey(jwtProperties().getPublicKeyPath());
    }

    /**
     * jwt decoder
     */
    @Bean
    public JwtDecoder jwtDecoder(RSAPublicKey rsaPublicKey) {
        return NimbusJwtDecoder.withPublicKey(rsaPublicKey).build();
    }

    /**
     * Converter
     * JWT -> {@link org.springframework.security.core.Authentication}
     */
    @Bean
    public JwtAuthenticationSupportConverter supportConverter() {
        return new JwtAuthenticationSupportConverter(new JwtUserPrincipalSupportConverter(), new JwtAuthenticationConverter());
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
            .jwtAuthenticationConverter(jwt -> supportConverter().convert(jwt))
        ;
    }

}