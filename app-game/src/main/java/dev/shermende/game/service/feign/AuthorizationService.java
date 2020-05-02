package dev.shermende.game.service.feign;

import dev.shermende.game.interceptor.FeignInterceptor;
import dev.shermende.lib.model.authorization.UserModel;
import dev.shermende.support.spring.component.annotation.InterceptResult;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@FeignClient(value = "env-authorization", fallbackFactory = AuthorizationService.AuthorizationServiceFallback.class)
public interface AuthorizationService {

    @PostMapping("/introspect")
    @InterceptResult(FeignInterceptor.class)
    Optional<UserModel> introspect();

    @Component
    class AuthorizationServiceFallback implements FallbackFactory<AuthorizationService> {

        @Override
        public AuthorizationService create(Throwable throwable) {
            return Optional::empty;
        }
    }

}
