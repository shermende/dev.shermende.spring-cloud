package dev.shermende.reference.db.entity.movement;

import dev.shermende.lib.dal.db.entity.TimedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MovementReason extends TimedEntity<Long> {

    private static final long serialVersionUID = -5368034288441153128L;

    private String intro;

}
