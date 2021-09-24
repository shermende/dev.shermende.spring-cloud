package dev.shermende.reference.controller;

import dev.shermende.reference.assembler.MovementPointModelAssembler;
import dev.shermende.reference.db.repository.movement.MovementPointRepository;
import dev.shermende.reference.exception.NotFoundException;
import dev.shermende.reference.lib.api.MovementPointApiService;
import dev.shermende.reference.lib.model.MovementPointModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MovementPointController implements MovementPointApiService {

    private final MovementPointRepository repository;
    private final MovementPointModelAssembler assembler;

    @Override
    public MovementPointModel findById(
            @PathVariable Long id
    ) {
        return assembler.toModel(repository.findById(id).orElseThrow(NotFoundException::new));
    }

}
