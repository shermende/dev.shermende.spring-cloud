package dev.shermende.reference.controller;

import dev.shermende.reference.assembler.MovementPointModelAssembler;
import dev.shermende.reference.exception.NotFoundException;
import dev.shermende.reference.lib.api.MovementPointApiService;
import dev.shermende.reference.lib.model.MovementPointModel;
import dev.shermende.reference.service.MovementPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MovementPointController implements MovementPointApiService {

    private final MovementPointService service;
    private final MovementPointModelAssembler assembler;

    @Override
    public MovementPointModel findById(
            @PathVariable Long id
    ) {
        return assembler.toModel(service.findById(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public MovementPointModel findScenarioPointByIndex(
            @RequestParam Long scenarioId,
            @RequestParam Integer index
    ) {
        return assembler.toModel(service.findScenarioPointByIndex(scenarioId, index));
    }

    @Override
    public Long countByScenario(
            @RequestParam Long scenarioId
    ) {
        return service.countByScenarioId(scenarioId);
    }

}
