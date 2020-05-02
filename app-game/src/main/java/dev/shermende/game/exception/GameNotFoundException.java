package dev.shermende.game.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Game not found")
public class GameNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -4036538504923394460L;
}
