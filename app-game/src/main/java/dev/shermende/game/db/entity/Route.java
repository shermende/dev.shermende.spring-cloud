package dev.shermende.game.db.entity;

import dev.shermende.lib.dal.db.entity.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Route extends BaseEntity<Long> {
    private static final long serialVersionUID = 1425335737055233411L;

    @NotNull
    private Long gameId;

    @NotNull
    private Long sourcePointId;

    @NotNull
    private Long reasonId;

    @NotNull
    private Long targetPointId;

}