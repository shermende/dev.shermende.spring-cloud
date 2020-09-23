package dev.shermende.game.configuration.oauth;

import dev.shermende.lib.security.configuration.ouath.OAuthProperties;
import dev.shermende.lib.security.configuration.ouath.OauthAccessTokenConverter;
import dev.shermende.lib.security.configuration.ouath.OauthRemoteTokenServices;
import dev.shermende.lib.security.configuration.ouath.OauthUserPrincipalConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.validation.annotation.Validated;

@Configuration
@Profile("oauth")
@EnableResourceServer
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OauthSecurityConfiguration extends ResourceServerConfigurerAdapter {

    /**
     * bean of oauth properties
     */
    @Bean
    @Validated
    @ConfigurationProperties("x-oauth")
    public OAuthProperties oAuthProperties() {
        return new OAuthProperties();
    }

    /**
     * oauth token service
     * provide {@link org.springframework.security.core.Authentication} by token
     */
    @Bean
    public ResourceServerTokenServices tokenService(
        OAuthProperties properties
    ) {
        final OauthRemoteTokenServices tokenServices = new OauthRemoteTokenServices();
        tokenServices.setClientId(properties.getClient());
        tokenServices.setClientSecret(properties.getSecret());
        tokenServices.setCheckTokenEndpointUrl(properties.getUrl());
        tokenServices.setAccessTokenConverter(new OauthAccessTokenConverter(new OauthUserPrincipalConverter()));
        return tokenServices;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/instances/**").permitAll()  // management port
            .antMatchers("/actuator/**").permitAll()  // management port
            .anyRequest().authenticated()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().cors().and().anonymous().disable().httpBasic().disable().csrf().disable()
        ;
    }

}