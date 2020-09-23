package dev.shermende.game.configuration.oauth;

import dev.shermende.lib.security.model.UserPrincipal;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
@Configuration
@Profile({"oauth"})
public class OauthFeignClientConfiguration {
    private static final String BEARER = "Bearer %s";

    @Bean
    public RequestInterceptor basicAuthRequestInterceptor() {
        return requestTemplate -> {
            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            requestTemplate.header(HttpHeaders.AUTHORIZATION, String.format(BEARER, new String(((UserPrincipal) authentication.getPrincipal()).token())));
        };
    }

}
