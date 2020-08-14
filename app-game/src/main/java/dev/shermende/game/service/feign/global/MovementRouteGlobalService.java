package dev.shermende.game.service.feign.global;

import dev.shermende.game.service.feign.MovementRouteService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;

@Profile({"!local"})
@FeignClient(contextId = "movementRoute", name = "app-reference", path = "/movementRoutes", fallbackFactory = MovementRouteService.MovementRouteServiceFallback.class)
public interface MovementRouteGlobalService extends MovementRouteService {

}