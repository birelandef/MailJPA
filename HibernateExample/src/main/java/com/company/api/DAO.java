package com.company.api;

import com.company.core.entity.Entity;

import java.util.Collection;

/**
 * TODO javadoc
 *
 * @author Sofia Ruban
 */
public interface DAO<T extends Entity> {

    /**
     * Add the entity to storage
     *
     * @param entity
     */
    void addEntity(T entity);

    /**
     * Get all entity from storage
     *
     * @return
     */
    Collection<T> getAllEntity(Class clazz);

    /**
     * Find entyty by id
     *
     * @param idEntity
     * @return found entity
     */
    T findEntityById(String idEntity);

    /**
     * Update the entity
     *
     * @param entity
     */
    void updateEntity(T entity);

    /**
     * Remove the entity
     *
     * @param entity
     */
    void removeEntity(T entity);
}
