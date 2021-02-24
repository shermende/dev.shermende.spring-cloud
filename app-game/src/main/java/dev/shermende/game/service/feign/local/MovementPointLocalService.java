package dev.shermende.game.service.feign.local;

import dev.shermende.game.service.feign.MovementPointService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;

@Profile({"local"})
@FeignClient(contextId = "movementPoint", value = "app-reference", url = "http://127.0.0.1:8100/movementPoints", fallbackFactory = MovementPointService.MovementPointServiceFallback.class)
public interface MovementPointLocalService extends MovementPointService {

}