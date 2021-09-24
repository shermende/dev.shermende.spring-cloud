package dev.shermende.reference.controller;

import dev.shermende.reference.assembler.MovementReasonModelAssembler;
import dev.shermende.reference.db.repository.movement.MovementReasonRepository;
import dev.shermende.reference.exception.NotFoundException;
import dev.shermende.reference.lib.api.MovementReasonApiService;
import dev.shermende.reference.lib.model.MovementReasonModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MovementReasonController implements MovementReasonApiService {

    private final MovementReasonRepository repository;
    private final MovementReasonModelAssembler assembler;

    @Override
    public MovementReasonModel findById(
            @PathVariable Long id
    ) {
        return assembler.toModel(repository.findById(id).orElseThrow(NotFoundException::new));
    }

}
