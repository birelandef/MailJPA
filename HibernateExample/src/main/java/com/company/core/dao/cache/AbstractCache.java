package com.company.core.dao.cache;

import com.company.api.DAO;
import com.company.core.entity.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO javadoc
 *
 * @author Sofia Ruban
 */
public abstract class AbstractCache<T extends Entity> implements DAO<T> {

    /**
     * Store entities
     */
    private Map<String, T> cache = new ConcurrentHashMap<String, T>();

    public void addEntity(T entity) {
        cache.put(entity.getId(), entity);
    }

    protected abstract Class<T> getClassEntity();

    public Collection<T> getAllEntity() {
        List<T> entities = new ArrayList<T>();
        for (Map.Entry<String, T> entry : cache.entrySet()) {
            T entity = entry.getValue();
            if (entity.getClass().isAssignableFrom(getClassEntity())) {
                entities.add((T) entity);
            }
        }
        return entities;
    }

    public T findEntityById(String idEntity) {
        return cache.get(idEntity);
    }

    public void updateEntity(T entity) {
        cache.put(entity.getId(), entity);
    }

    public void removeEntity(T entity) {
        cache.remove(entity.getId());
    }
}
