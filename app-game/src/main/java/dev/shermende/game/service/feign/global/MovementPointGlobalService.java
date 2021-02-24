package dev.shermende.game.service.feign.global;

import dev.shermende.game.service.feign.MovementPointService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;

@Profile({"!local"})
@FeignClient(contextId = "movementPoint", value = "app-reference", path = "/movementPoints", fallbackFactory = MovementPointService.MovementPointServiceFallback.class)
public interface MovementPointGlobalService extends MovementPointService {

}