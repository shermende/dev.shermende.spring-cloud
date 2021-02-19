package dev.shermende.authorization.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SecuredController {

    @GetMapping("/secured")
//    @PreAuthorize("#oauth2.hasScope('oauth2:any')")
    public Authentication secured(
        @AuthenticationPrincipal Authentication authentication
    ) {
        log.info("{}", authentication.getAuthorities());
        return authentication;
    }

}