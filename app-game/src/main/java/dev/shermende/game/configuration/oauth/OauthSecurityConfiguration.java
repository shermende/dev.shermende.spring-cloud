package dev.shermende.game.configuration.oauth;

import lombok.Data;
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
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@Profile({"oauth"})
@EnableResourceServer
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OauthSecurityConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests().anyRequest().authenticated()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
            .and().anonymous().disable().httpBasic().disable().csrf().disable()
        ;
    }

    @Bean
    @ConfigurationProperties("oauth.client")
    public OAuthClientProperties oAuthClientProperties() {
        return new OAuthClientProperties();
    }

    @Bean
    public ResourceServerTokenServices tokenService(
        OAuthClientProperties properties
    ) {
        final RemoteTokenServices tokenServices = new RemoteTokenServices();
        tokenServices.setClientId(properties.getClient());
        tokenServices.setClientSecret(properties.getSecret());
        tokenServices.setCheckTokenEndpointUrl(properties.getUrl());
        return tokenServices;
    }

    @Data
    public static class OAuthClientProperties {
        private String client = "client";
        private String secret = "secret";
        private String url = "http://localhost:8082/oauth/check_token";
    }

}