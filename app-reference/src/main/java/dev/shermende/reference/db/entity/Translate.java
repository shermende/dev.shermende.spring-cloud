package dev.shermende.reference.db.entity;

import dev.shermende.lib.dal.db.entity.TimedEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Translate extends TimedEntity<Long> {

    private static final long serialVersionUID = -5368034288441153128L;

    @NotEmpty
    @Size(min = 2, max = 2)
    private String locale;

    @NotEmpty
    @Size(max = 255)
    private String key;

    @NotEmpty
    @Size(max = 2048)
    private String value;

}
