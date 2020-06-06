package dev.shermende.game.service.feign;

import dev.shermende.game.interceptor.FeignInterceptor;
import dev.shermende.game.model.TranslateModel;
import dev.shermende.support.spring.component.annotation.InterceptResult;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(contextId = "translate", name = "app-reference", path = "/translates", fallbackFactory = TranslateService.TranslateServiceFallback.class)
public interface TranslateService {

    @GetMapping("/findOneByKey")
    @InterceptResult(FeignInterceptor.class)
    Optional<TranslateModel> findOneByKey(
        @RequestParam(value = "key") String key
    );

    @Component
    class TranslateServiceFallback implements FallbackFactory<TranslateService> {
        @Override
        public TranslateService create(Throwable throwable) {
            return key -> Optional.empty();
        }
    }

}
