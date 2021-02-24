package dev.shermende.game.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteCreateResource {

    @NotNull
    @Min(1)
    @Max(10000000)
    private Long gameId;

    @NotNull
    @Min(1)
    @Max(10000000)
    private Long sourcePointId;

    @NotNull
    @Min(1)
    @Max(10000000)
    private Long reasonId;

    @NotNull
    @Min(1)
    @Max(10000000)
    private Long targetPointId;

}