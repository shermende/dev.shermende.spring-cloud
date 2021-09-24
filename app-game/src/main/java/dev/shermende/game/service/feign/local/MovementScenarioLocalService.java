package dev.shermende.game.service.feign.local;

import dev.shermende.reference.lib.api.MovementScenarioApiService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;

@Profile({"local"})
@FeignClient(contextId = "movementScenario", value = "app-reference", url = "http://127.0.0.1:8100")
public interface MovementScenarioLocalService extends MovementScenarioApiService {

}
