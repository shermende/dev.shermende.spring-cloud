package dev.shermende.game.service.feign.global;

import dev.shermende.game.service.feign.MovementScenarioService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;

@Profile({"!local"})
@FeignClient(contextId = "movementScenario", name = "app-reference", path = "/movementScenarios", fallbackFactory = MovementScenarioService.MovementScenarioServiceFallback.class)
public interface MovementScenarioGlobalService extends MovementScenarioService {

}
