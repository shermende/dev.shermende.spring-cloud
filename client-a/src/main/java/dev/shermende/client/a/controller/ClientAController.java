package dev.shermende.client.a.controller;

import dev.shermende.client.a.service.ClientBService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientAController {

    private final ClientBService clientBService;

    public ClientAController(ClientBService clientBService) {
        this.clientBService = clientBService;
    }

    @GetMapping
    public String get(
        @Value("${spring.application.name}") String application
    ) {
        return application;
    }

    @GetMapping("/feign")
    public String feign() {
        return clientBService.get();
    }

}
