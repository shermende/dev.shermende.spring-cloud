package dev.shermende.game.configuration;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

@Configuration
public class FeignClientConfiguration {
    private static final String BEARER = "Bearer %s";

    @Bean
    public RequestInterceptor basicAuthRequestInterceptor() {
        return requestTemplate -> {
            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            requestTemplate.header(HttpHeaders.ACCEPT_LANGUAGE, LocaleContextHolder.getLocale().getLanguage());
            requestTemplate.header(HttpHeaders.AUTHORIZATION, String.format(BEARER, ((Jwt) authentication.getPrincipal()).getTokenValue()));
        };
    }

}
