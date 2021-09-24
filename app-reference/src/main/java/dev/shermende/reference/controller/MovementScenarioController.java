package dev.shermende.reference.controller;

import com.querydsl.core.types.Predicate;
import dev.shermende.reference.assembler.MovementScenarioModelAssembler;
import dev.shermende.reference.db.entity.movement.MovementScenario;
import dev.shermende.reference.exception.NotFoundException;
import dev.shermende.reference.model.MovementScenarioModel;
import dev.shermende.reference.service.MovementScenarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movementScenarios")
public class MovementScenarioController {

    private final MovementScenarioService service;
    private final MovementScenarioModelAssembler assembler;

    @GetMapping("/{id}")
    public MovementScenarioModel findById(
            @PathVariable Long id
    ) {
        return assembler.toModel(service.findById(id).orElseThrow(NotFoundException::new));
    }

    @GetMapping
    public PagedModel<MovementScenarioModel> findAll(
            @PageableDefault Pageable pageable,
            @QuerydslPredicate(root = MovementScenario.class) Predicate predicate,
            PagedResourcesAssembler<MovementScenario> pagedResourcesAssembler
    ) {
        return pagedResourcesAssembler.toModel(service.findAll(predicate, pageable), assembler);
    }

}
