package dev.shermende.reference.lib.api;

import dev.shermende.reference.lib.model.MovementPointModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface MovementPointApiService {

    @GetMapping("/movement-point/{id}")
    MovementPointModel findById(
            @NotNull @PathVariable Long id
    );

    @GetMapping("/movement-point/find-ids-by-scenario-id")
    List<Long> findIdsByScenarioId(
            @NotNull @RequestParam("scenario-id") Long scenarioId
    );

    @GetMapping("/movement-point/find-by-scenario-and-index")
    MovementPointModel findPointByScenarioIdAndIndex(
            @NotNull @RequestParam("scenario-id") Long scenarioId,
            @NotNull @Min(0) @RequestParam Integer index
    );

    @GetMapping("/movement-point/count-by-scenario")
    Long countPointsByScenario(
            @NotNull @RequestParam("scenario-id") Long scenarioId
    );

}
