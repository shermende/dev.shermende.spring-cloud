package dev.shermende.game.service.feign;

import dev.shermende.game.resource.BalabobaRequestResource;
import dev.shermende.game.resource.BalabobaResponseResource;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(value = "BalabobaService", url = "https://zeapi.yandex.net", fallbackFactory = BalabobaService.BalabobaServiceFallback.class)
public interface BalabobaService {

    @PostMapping("/lab/api/yalm/text3")
    Optional<BalabobaResponseResource> introspect(
            @RequestBody BalabobaRequestResource request
    );

    @Slf4j
    @Component
    class BalabobaServiceFallback implements FallbackFactory<BalabobaService> {
        @Override
        public BalabobaService create(Throwable throwable) {
            log.error(throwable.getMessage(), throwable);
            return request -> Optional.empty();
        }
    }

}
