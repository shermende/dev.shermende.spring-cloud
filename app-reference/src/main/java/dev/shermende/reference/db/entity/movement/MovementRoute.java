package dev.shermende.reference.db.entity.movement;

import dev.shermende.lib.support.dal.db.entity.TimedEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MovementRoute extends TimedEntity<Long> {

    private static final long serialVersionUID = -5368034288441153128L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_point_id")
    private MovementPoint sourcePoint;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reason_id")
    private MovementReason reason;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_point_id")
    private MovementPoint targetPoint;

    @Column(name = "source_point_id", insertable = false, updatable = false)
    private Long sourcePointId;

    @Column(name = "reason_id", insertable = false, updatable = false)
    private Long reasonId;

    @Column(name = "target_point_id", insertable = false, updatable = false)
    private Long targetPointId;

}
