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
public class MovementPoint extends TimedEntity<Long> {

    private static final long serialVersionUID = -5368034288441153128L;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "m2m_point_quest",
        joinColumns = @JoinColumn(name = "point_id"),
        inverseJoinColumns = @JoinColumn(name = "quest_id"))
    private List<Quest> quests;

}