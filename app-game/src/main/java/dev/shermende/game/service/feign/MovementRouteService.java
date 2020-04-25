package dev.shermende.game.service.feign;

import dev.shermende.lib.model.reference.MovementRouteModel;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(contextId = "movementRoute", name = "app-reference", path = "/movementRoutes", fallbackFactory = MovementRouteService.MovementMapServiceFallback.class)
public interface MovementRouteService {

    @GetMapping("/custom/findOneBySourcePointIdAndReasonId")
    Optional<MovementRouteModel> findBySourcePointIdAndReasonId(
        @RequestParam("sourcePointId") Long sourcePointId,
        @RequestParam("reasonId") Long reasonId
    );

    @Component
    class MovementMapServiceFallback implements FallbackFactory<MovementRouteService> {
        @Override
        public MovementRouteService create(Throwable throwable) {
            return (sourcePointId, reasonId) -> Optional.empty();
        }
    }

}
