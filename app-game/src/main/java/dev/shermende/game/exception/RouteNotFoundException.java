package dev.shermende.game.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Route not found")
public class RouteNotFoundException extends RuntimeException{
    private static final long serialVersionUID = -4036538504923394460L;
}
