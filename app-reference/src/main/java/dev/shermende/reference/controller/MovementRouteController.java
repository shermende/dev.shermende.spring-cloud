package dev.shermende.reference.controller;

import dev.shermende.reference.assembler.MovementRouteModelAssembler;
import dev.shermende.reference.db.entity.movement.MovementRoute;
import dev.shermende.reference.exception.NotFoundException;
import dev.shermende.reference.model.MovementRouteModel;
import dev.shermende.reference.service.MovementRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movementRoutes")
public class MovementRouteController {

    private final MovementRouteService service;
    private final MovementRouteModelAssembler assembler;

    @GetMapping("/{id}")
    public MovementRouteModel findById(
            @PathVariable Long id
    ) {
        return assembler.toModel(service.findById(id).orElseThrow(NotFoundException::new));
    }

    @GetMapping("/findAllBySourcePointId")
    public PagedModel<MovementRouteModel> findAllBySourcePointId(
        @PageableDefault Pageable pageable,
        @RequestParam("sourcePointId") Long sourcePointId,
        PagedResourcesAssembler<MovementRoute> pagedResourcesAssembler
    ) {
        return pagedResourcesAssembler.toModel(service.findAllBySourcePointId(pageable, sourcePointId), assembler);
    }

}
