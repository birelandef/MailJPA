package edu.mail.core.dao;

import edu.mail.core.entities.Entity;

import java.util.Collection;

/**
 * TODO javadoc
 *
 * @author Sofia Ruban
 */
public interface DAO<T extends Entity> {

    /**
     * Add the entities to storage
     *
     * @param entity
     */
    void addEntity(T entity);

    /**
     * Get all entities from storage
     *
     * @return
     */
    Collection<T> getAllEntity(Class clazz);

    /**
     * Find entyty by id
     *
     * @param idEntity
     * @return found entities
     */
    T findEntityById(String idEntity);

    /**
     * Update the entities
     *
     * @param entity
     */
    void updateEntity(T entity);

    /**
     * Remove the entities
     *
     * @param entity
     */
    void removeEntity(T entity);
}
