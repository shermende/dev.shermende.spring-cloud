package dev.shermende.game.service.feign;

import dev.shermende.game.interceptor.FeignInterceptor;
import dev.shermende.game.model.MovementScenarioModel;
import dev.shermende.support.spring.aop.intercept.annotation.InterceptResult;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public interface MovementScenarioService {

    @GetMapping("/{id}")
    @InterceptResult(FeignInterceptor.class)
    Optional<MovementScenarioModel> findById(
            @PathVariable Long id
    );

    @Slf4j
    @Component
    class MovementScenarioServiceFallback implements FallbackFactory<MovementScenarioService> {
        @Override
        public MovementScenarioService create(Throwable throwable) {
            log.error(throwable.getMessage());
            return id -> Optional.empty();
        }
    }

}
