package dev.shermende.game.configuration.oauth;

import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

@Slf4j
@Configuration
@Profile({"oauth"})
public class OauthFeignClientConfiguration {
    private static final String BEARER = "Bearer %s";

    @Bean
    public RequestInterceptor basicAuthRequestInterceptor() {
        return requestTemplate -> {
            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            final OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
            final OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) oAuth2Authentication.getDetails();
            requestTemplate.header(HttpHeaders.AUTHORIZATION, String.format(BEARER, details.getTokenValue()));
        };
    }

}
