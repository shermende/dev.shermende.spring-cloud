package dev.shermende.game.service.feign.local;

import dev.shermende.game.service.feign.MovementRouteService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;

@Profile({"local"})
@FeignClient(contextId = "movementRoute", value = "app-reference", url = "http://127.0.0.1:8100/movementRoutes", fallbackFactory = MovementRouteService.MovementRouteServiceFallback.class)
public interface MovementRouteLocalService extends MovementRouteService {

}