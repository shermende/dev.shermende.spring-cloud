package dev.shermende.game.configuration;

import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

@Slf4j
@Configuration
public class FeignClientConfiguration {
    private static final String BEARER = "Bearer %s";

    @Bean
    public RequestInterceptor basicAuthRequestInterceptor() {
        return requestTemplate -> {
            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            requestTemplate.header(HttpHeaders.AUTHORIZATION, String.format(BEARER, ((Jwt) authentication.getPrincipal()).getTokenValue()));
        };
    }

}
