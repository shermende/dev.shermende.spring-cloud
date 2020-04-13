package dev.shermende.game;

import dev.shermende.game.feign.TranslateService;
import dev.shermende.lib.model.reference.TranslateModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.hateoas.PagedModel;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@EnableFeignClients
@SpringBootApplication
@RequiredArgsConstructor
public class ApplicationGame {

    public static void main(String... args) {
        SpringApplication.run(ApplicationGame.class);
    }

    private final TranslateService translateService;

    @GetMapping("/")
    public void get(@AuthenticationPrincipal JwtAuthenticationToken token) {
        final PagedModel<TranslateModel> page = translateService.translate(String.format("Bearer %s", token.getToken().getTokenValue()));
        log.info("asdsad");
    }
}
