package dev.shermende.reference.lib.api;

import dev.shermende.reference.lib.model.MovementPointModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface MovementPointApiService {

    @GetMapping("/movement-point/{id}")
    MovementPointModel findById(
            @PathVariable Long id
    );

}
