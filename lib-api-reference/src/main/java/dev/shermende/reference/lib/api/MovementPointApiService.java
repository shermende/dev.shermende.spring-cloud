package dev.shermende.reference.lib.api;

import dev.shermende.reference.lib.model.MovementPointModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface MovementPointApiService {

    @GetMapping("/movement-point/{id}")
    MovementPointModel findById(
            @PathVariable Long id
    );

    @GetMapping("/movement-point/find-by-scenario-and-index")
    MovementPointModel findScenarioPointByIndex(
            @RequestParam Long scenarioId,
            @RequestParam Integer index
    );

    @GetMapping("/movement-point/count-by-scenario")
    Long countByScenario(
            @RequestParam Long scenarioId
    );

}
