package dev.shermende.game.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -4036538504923394460L;
}
