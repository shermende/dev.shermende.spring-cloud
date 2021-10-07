package dev.shermende.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AuthorizationApplication    {

    public static void main(String... args) {
        SpringApplication.run(AuthorizationApplication.class);
    }

}
