package dev.shermende.authorization.db.entity;

import dev.shermende.lib.support.db.entity.TimedEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
    @Column(unique = true)
    @Size(min = 3, max = 64)
    private String email;

    @NotEmpty
    @Size(min = 16, max = 128)
    private String password;

}
