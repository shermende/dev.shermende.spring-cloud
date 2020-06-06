package dev.shermende.game.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Scenario not found")
public class ScenarioNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -4036538504923394460L;
}
