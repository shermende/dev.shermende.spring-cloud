package dev.shermende.game.service.feign;

import dev.shermende.game.interceptor.FeignInterceptor;
import dev.shermende.game.model.MovementRouteModel;
import dev.shermende.support.spring.component.annotation.InterceptResult;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(contextId = "movementRoute", name = "app-reference", path = "/movementRoutes", fallbackFactory = MovementRouteService.MovementMapServiceFallback.class)
public interface MovementRouteService {

    @GetMapping("/{id}")
    @InterceptResult(FeignInterceptor.class)
    Optional<MovementRouteModel> findById(
        @PathVariable Long id
    );

    @Component
    class MovementMapServiceFallback implements FallbackFactory<MovementRouteService> {
        @Override
        public MovementRouteService create(Throwable throwable) {
            return id -> Optional.empty();
        }
    }

}
