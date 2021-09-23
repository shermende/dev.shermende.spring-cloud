package dev.shermende.reference.db.entity;

import dev.shermende.lib.dal.db.entity.TimedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Translate translate = (Translate) o;
        return Objects.equals(locale, translate.locale) && Objects.equals(key, translate.key) && Objects.equals(value, translate.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), locale, key, value);
    }

}
