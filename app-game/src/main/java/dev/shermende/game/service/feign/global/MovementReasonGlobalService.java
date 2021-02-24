package dev.shermende.game.service.feign.global;

import dev.shermende.game.service.feign.MovementPointService;
import dev.shermende.game.service.feign.MovementReasonService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;

@Profile({"!local"})
@FeignClient(contextId = "movementReason", value = "app-reference", path = "/movementReasons", fallbackFactory = MovementPointService.MovementPointServiceFallback.class)
public interface MovementReasonGlobalService extends MovementReasonService {

}