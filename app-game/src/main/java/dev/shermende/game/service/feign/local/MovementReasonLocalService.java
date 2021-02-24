package dev.shermende.game.service.feign.local;

import dev.shermende.game.service.feign.MovementPointService;
import dev.shermende.game.service.feign.MovementReasonService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;

@Profile({"local"})
@FeignClient(contextId = "movementReason", value = "app-reference", url = "http://127.0.0.1:8100/movementReasons", fallbackFactory = MovementPointService.MovementPointServiceFallback.class)
public interface MovementReasonLocalService extends MovementReasonService {

}