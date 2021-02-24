package dev.shermende.game.validator;

import dev.shermende.game.db.entity.Game;
import dev.shermende.game.db.entity.Route;
import dev.shermende.game.exception.NotFoundException;
import dev.shermende.game.resource.GameMoveResource;
import dev.shermende.game.service.crud.GameCrudService;
import dev.shermende.game.service.crud.RouteCrudService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GameMoveResourceValidator extends AbstractHttpValidator {

    private final GameCrudService gameCrudService;
    private final RouteCrudService routeCrudService;

    @Override
    public boolean supports(@NotNull Class<?> aClass) {
        return GameMoveResource.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(@NotNull Object o, @NotNull Errors errors) {
        if (isNotExistPathVariable(ID)) throw new NotFoundException();
        final GameMoveResource resource = (GameMoveResource) o;
        if (Objects.isNull(resource.getRouteId())) return;

        final Optional<Game> game = gameCrudService.findById(Long.valueOf(getPathVariable(ID)));
        final Optional<Route> route = routeCrudService.findById(resource.getRouteId());

        if (!route.isPresent())
            errors.rejectValue("routeId", "not-found", new Object[]{}, "not-found");
        if (game.isPresent() && route.isPresent() && !route.get().getSourcePointId().equals(game.get().getPointId()))
            errors.rejectValue("routeId", "wrong-source-point", new Object[]{}, "wrong-source-point");
    }

}
