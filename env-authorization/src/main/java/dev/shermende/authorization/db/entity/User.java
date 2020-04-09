package dev.shermende.authorization.db.entity;

import dev.shermende.lib.jpa.entity.TimedEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
public class User extends TimedEntity<Long> {
    private static final long serialVersionUID = -2683732998281446313L;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

}
