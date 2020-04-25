package dev.shermende.reference.db.entity.movement;

import dev.shermende.lib.db.entity.TimedEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MovementScenario extends TimedEntity<Long> {

    private static final long serialVersionUID = -5368034288441153128L;

    @NotEmpty
    @Size(min = 2, max = 255)
    private String title;

    @NotEmpty
    @Size(min = 2, max = 2048)
    private String description;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reason_id")
    private MovementReason reason;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "point_id")
    private MovementPoint point;

    @Column(name = "reason_id", insertable = false, updatable = false)
    private Long reasonId;

    @Column(name = "point_id", insertable = false, updatable = false)
    private Long pointId;

}
