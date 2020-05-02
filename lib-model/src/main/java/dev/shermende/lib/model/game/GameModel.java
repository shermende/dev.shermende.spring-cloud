package dev.shermende.lib.model.game;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
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

    private Long userId;

    private Long scenarioId;

    private Long reasonId;

    private Long pointId;

    private String user;

    private String scenario;

    private String reason;

    private String point;


}
