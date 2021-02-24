package dev.shermende.game.processor;

import dev.shermende.game.db.entity.Game;
import dev.shermende.game.model.MovementPointModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RouteGenerateProcessorCtx {
    private Game game;
    private MovementPointModel source;
    private MovementPointModel target;
}