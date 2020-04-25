package dev.shermende.game.service.feign;

import dev.shermende.lib.model.reference.MovementScenarioModel;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(contextId = "movementScenario", name = "app-reference", path = "/movementScenarios", fallbackFactory = MovementScenarioService.MovementScenarioServiceFallback.class)
public interface MovementScenarioService {

    @GetMapping("/{id}")
    Optional<MovementScenarioModel> findById(
        @PathVariable Long id
    );

    @Component
    class MovementScenarioServiceFallback implements FallbackFactory<MovementScenarioService> {
        @Override
        public MovementScenarioService create(Throwable throwable) {
            return id -> Optional.empty();
        }
    }

}
