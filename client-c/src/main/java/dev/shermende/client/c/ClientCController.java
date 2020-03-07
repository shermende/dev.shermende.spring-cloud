package dev.shermende.client.c;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientCController {

    @GetMapping
    public String get(
        @Value("${spring.application.name}") String application
    ) {
        return application;
    }

}
