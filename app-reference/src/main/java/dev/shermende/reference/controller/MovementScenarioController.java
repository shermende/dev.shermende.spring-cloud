package dev.shermende.reference.controller;

import dev.shermende.lib.model.reference.MovementScenarioModel;
import dev.shermende.reference.assembler.MovementScenarioModelAssembler;
import dev.shermende.reference.db.entity.movement.MovementScenario;
import dev.shermende.reference.db.repository.movement.MovementScenarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movementScenarios")
public class MovementScenarioController {

    private final MovementScenarioRepository repository;
    private final MovementScenarioModelAssembler assembler;

    @GetMapping("/custom/findAll")
    public PagedModel<MovementScenarioModel> findAll(
        @PageableDefault Pageable pageable,
        PagedResourcesAssembler<MovementScenario> pagedResourcesAssembler
    ) {
        return pagedResourcesAssembler.toModel(repository.findAll(pageable), assembler);
    }

}
