package com.company.core.entity;

import com.company.core.utils.IdGenerator;

import javax.persistence.*;

/**
 * Parent com.company.core.entity for all entities of the project
 *
 * @author Sofia Ruban
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Entity{

    /**
     * Identifier com.company.core.entity
     */
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    private String id;

    public Entity() {
        id = IdGenerator.generatorId();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public abstract String toString();
}
