package dev.shermende.game.service.feign;

import dev.shermende.game.interceptor.FeignInterceptor;
import dev.shermende.game.model.TranslateModel;
import dev.shermende.support.spring.aop.intercept.annotation.InterceptResult;
import feign.hystrix.FallbackFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public interface TranslateService {

    @GetMapping("/findOneByKey")
    @InterceptResult(FeignInterceptor.class)
    Optional<TranslateModel> findOneByKey(
        @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE) String locale,
        @RequestParam(value = "key") String key
    );

    @Component
    class TranslateServiceFallback implements FallbackFactory<TranslateService> {
        @Override
        public TranslateService create(Throwable throwable) {
            return (locale, key) -> Optional.empty();
        }
    }

}
