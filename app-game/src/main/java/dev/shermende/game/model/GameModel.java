package dev.shermende.game.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import dev.shermende.reference.lib.model.MovementPointModel;
import dev.shermende.reference.lib.model.MovementScenarioModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Relation(value = "item", collectionRelation = "data")
public class GameModel extends RepresentationModel<GameModel> {

    private Long id;

    private MovementScenarioModel scenario;

    private MovementPointModel source;

    private MovementPointModel target;

    @NotNull
    @Override
    @JsonIgnore
    public Links getLinks() {
        return super.getLinks();
    }

}
