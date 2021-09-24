package dev.shermende.reference.service.feign;

import dev.shermende.reference.resource.BalabobaRequestResource;
import dev.shermende.reference.resource.BalabobaResponseResource;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public interface BalabobaService {

    @PostMapping("/lab/api/yalm/text3")
    Optional<BalabobaResponseResource> introspect(
            @RequestBody BalabobaRequestResource request
    );

    @Slf4j
    @Component
    class AuthorizationServiceFallback implements FallbackFactory<BalabobaService> {
        @Override
        public BalabobaService create(Throwable throwable) {
            log.error(throwable.getMessage(), throwable);
            return request -> Optional.empty();
        }
    }

}
