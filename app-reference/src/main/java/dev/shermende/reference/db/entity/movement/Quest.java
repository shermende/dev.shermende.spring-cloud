package dev.shermende.reference.db.entity.movement;

import dev.shermende.lib.dal.db.entity.TimedEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Quest extends TimedEntity<Long> {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "m2m_point_quest",
        joinColumns = @JoinColumn(name = "quest_id"),
        inverseJoinColumns = @JoinColumn(name = "point_id"))
    private List<MovementPoint> points;

}