package dev.shermende.lib.db.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@MappedSuperclass
public abstract class BaseEntity<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 3584406775766359057L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private T id;

}
