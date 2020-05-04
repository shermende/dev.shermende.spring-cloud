package dev.shermende.game.db.entity;

import dev.shermende.lib.db.entity.BaseEntity;
import lombok.*;
import org.jetbrains.annotations.Nullable;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Game extends BaseEntity<Long> {
    private static final long serialVersionUID = 1425335737055233411L;

    @Nullable
    private Long routeId;

    @NotNull
    private Long userId;

    @NotNull
    private Long scenarioId;

    @NotNull
    private Long reasonId;

    @NotNull
    private Long pointId;

}
