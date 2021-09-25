package dev.shermende.reference.controller;

import dev.shermende.reference.assembler.MovementPointModelAssembler;
import dev.shermende.reference.exception.NotFoundException;
import dev.shermende.reference.lib.api.MovementPointApiService;
import dev.shermende.reference.lib.model.MovementPointModel;
import dev.shermende.reference.service.MovementPointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class MovementPointController implements MovementPointApiService {

    private final MovementPointService service;
    private final MovementPointModelAssembler assembler;

    @Override
    public MovementPointModel findById(
            @NotNull @PathVariable Long id
    ) {
        return assembler.toModel(service.findById(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public MovementPointModel findPointByScenarioIdAndIndex(
            @NotNull @RequestParam("scenario-id") Long scenarioId,
            @NotNull @Min(0) @RequestParam Integer index
    ) {
        return assembler.toModel(service.findPointByScenarioIdAndIndex(scenarioId, index).orElseThrow(NotFoundException::new));
    }

    @Override
    public Long countPointsByScenario(
            @NotNull @RequestParam("scenario-id") Long scenarioId
    ) {
        return service.countByScenarioId(scenarioId);
    }

}
