package dev.shermende.game.service.feign.local;

import dev.shermende.game.service.feign.MovementScenarioService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;

@Profile({"local"})
@FeignClient(contextId = "movementScenario", value = "app-reference", url = "http://127.0.0.1:8100/movementScenarios", fallbackFactory = MovementScenarioService.MovementScenarioServiceFallback.class)
public interface MovementScenarioLocalService extends MovementScenarioService {

}
