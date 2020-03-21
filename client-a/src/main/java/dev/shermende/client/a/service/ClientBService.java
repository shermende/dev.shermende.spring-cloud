package dev.shermende.client.a.service;

import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "client-b", fallbackFactory = ClientBService.ClientBServiceFallbackFactory.class)
public interface ClientBService {

    @GetMapping("/")
    String get();

    @Component
    class ClientBServiceFallbackFactory implements FallbackFactory<ClientBService> {

        @Override
        public ClientBService create(Throwable throwable) {
            return () -> "hystrix";
        }
    }

}
