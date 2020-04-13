package dev.shermende.lib.db.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class TimedEntity<T extends Serializable> extends BaseEntity<T> {

    private static final long serialVersionUID = 9203929628855316941L;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onPreUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @NotNull
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
