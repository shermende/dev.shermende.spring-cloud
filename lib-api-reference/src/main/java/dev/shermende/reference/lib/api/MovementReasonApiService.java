package dev.shermende.reference.lib.api;

import dev.shermende.reference.lib.model.MovementReasonModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface MovementReasonApiService {

    @GetMapping("/movement-reason/{id}")
    MovementReasonModel findById(
            @PathVariable Long id
    );

}
