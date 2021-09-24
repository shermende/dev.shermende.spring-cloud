package dev.shermende.game.db.entity;

import dev.shermende.lib.dal.db.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GraphMap extends BaseEntity<Long> {
    private static final long serialVersionUID = 1425335737055233411L;

    @NotNull
    private Long userId;

    @NotNull
    private Long gameId;

    @NotNull
    private Long sourceId;

    @NotNull
    private Long targetId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GraphMap graphMap = (GraphMap) o;
        return Objects.equals(userId, graphMap.userId) && Objects.equals(gameId, graphMap.gameId) && Objects.equals(sourceId, graphMap.sourceId) && Objects.equals(targetId, graphMap.targetId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, gameId, sourceId, targetId);
    }
}
