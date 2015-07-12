package edu.mail.core.entities;

import edu.mail.core.utils.IdGenerator;

import javax.persistence.*;

/**
 * Parent class for all entities of the project Mail Client
 *
 * @author Sofia Ruban
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Entity{

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    private String id;

    public Entity() {
        id = IdGenerator.generatorId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Entity entity = (Entity) o;
        return id.equals(entity.id);
    }

    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
