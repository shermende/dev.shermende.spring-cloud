package dev.shermende.authorization.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SecuredController {

    @GetMapping("/secured")
    @PreAuthorize("#oauth2.hasScope('oauth2:any')")
    public Authentication secured(
        @AuthenticationPrincipal OAuth2Authentication authentication
    ) {
        log.info("Scopes: {}", authentication.getOAuth2Request().getScope());
        log.info("Authorities: {}", authentication.getAuthorities());
        return authentication;
    }

}