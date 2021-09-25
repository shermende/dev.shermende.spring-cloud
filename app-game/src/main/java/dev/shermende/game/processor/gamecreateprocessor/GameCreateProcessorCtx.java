package dev.shermende.game.processor.gamecreateprocessor;

import dev.shermende.game.db.entity.Game;
import dev.shermende.game.resource.GameCreateResource;
import dev.shermende.lib.security.model.impl.PrincipalUser;
import dev.shermende.reference.lib.model.MovementScenarioModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameCreateProcessorCtx {

    private GameCreateResource resource;

    private PrincipalUser user;

    private Integer edgeChance;

    private Long reasonsCount;

    private Long pointsCount;

    private MovementScenarioModel scenario;

    private Game game;

}
