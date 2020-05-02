package dev.shermende.lib.model.reference;

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
public class MovementRouteModel extends RepresentationModel<MovementRouteModel> {

    private Long id;

    private Long sourcePointId;

    private Long reasonId;

    private Long targetPointId;

    private String sourcePoint;

    private String reason;

    private String targetPoint;

}
