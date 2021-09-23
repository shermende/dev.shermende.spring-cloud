package dev.shermende.reference.db.entity.movement;

import dev.shermende.lib.dal.db.entity.TimedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MovementRoute extends TimedEntity<Long> {

    private static final long serialVersionUID = -5368034288441153128L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_point_id")
    @ToString.Exclude
    private MovementPoint sourcePoint;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reason_id")
    @ToString.Exclude
    private MovementReason reason;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_point_id")
    @ToString.Exclude
    private MovementPoint targetPoint;

    @Column(name = "source_point_id", insertable = false, updatable = false)
    private Long sourcePointId;

    @Column(name = "reason_id", insertable = false, updatable = false)
    private Long reasonId;

    @Column(name = "target_point_id", insertable = false, updatable = false)
    private Long targetPointId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MovementRoute that = (MovementRoute) o;
        return Objects.equals(sourcePointId, that.sourcePointId) && Objects.equals(reasonId, that.reasonId) && Objects.equals(targetPointId, that.targetPointId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sourcePointId, reasonId, targetPointId);
    }
}
