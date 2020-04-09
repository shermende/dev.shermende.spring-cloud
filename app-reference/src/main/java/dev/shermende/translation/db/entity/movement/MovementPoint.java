package dev.shermende.translation.db.entity.movement;

import dev.shermende.lib.jpa.entity.TimedEntity;
import lombok.*;

import javax.persistence.Entity;

@Data
@Entity
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MovementPoint extends TimedEntity<Long> {

    private static final long serialVersionUID = -5368034288441153128L;

}
