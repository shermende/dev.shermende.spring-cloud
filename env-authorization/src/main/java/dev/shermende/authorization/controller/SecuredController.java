package dev.shermende.authorization.controller;

import dev.shermende.lib.security.model.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/secured")
public class SecuredController {

    @GetMapping("/oauth2-none")
    @PreAuthorize("#oauth2.hasScope('oauth2:none')")
    public UserPrincipal scopesUser(
        @AuthenticationPrincipal OAuth2Authentication authentication
    ) {
        return (UserPrincipal) authentication.getPrincipal();
    }

    @GetMapping("/authority-none")
    @PreAuthorize("hasAnyAuthority('ROLE_NONE')")
    public UserPrincipal authoritiesUser(
        @AuthenticationPrincipal OAuth2Authentication authentication
    ) {
        return (UserPrincipal) authentication.getPrincipal();
    }

}