package dev.shermende.game.service.feign;

import dev.shermende.game.interceptor.FeignInterceptor;
import dev.shermende.game.model.UserModel;
import dev.shermende.support.spring.component.annotation.InterceptResult;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

public interface AuthorizationService {

    @PostMapping("/introspect")
    @InterceptResult(FeignInterceptor.class)
    Optional<UserModel> introspect();

    @Slf4j
    @Component
    class AuthorizationServiceFallback implements FallbackFactory<AuthorizationService> {

        @Override
        public AuthorizationService create(Throwable throwable) {
            log.error(throwable.getMessage());
            return Optional::empty;
        }
    }

}
