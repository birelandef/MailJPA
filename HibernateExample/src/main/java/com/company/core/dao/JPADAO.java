package com.company.core.dao;

import com.company.api.DAO;
import com.company.core.entity.*;
import com.company.core.entity.Entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * @author Sophie
 * @date 27.06.2015.
 */
public class JPADAO<T extends Entity> implements DAO<T> {

    private EntityManagerFactory emf = null;
    private EntityManager em = null;
    private EntityTransaction tx = null;

    public JPADAO(String persistenceUnit ) {
        emf = Persistence.createEntityManagerFactory(persistenceUnit);
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    public void stopEntityManager(){
        em.close();
		emf.close();
    }

    @Override
    public void addEntity(T entity) {
        tx.begin();
        em.persist(entity);
        tx.commit();
    }

    @Override
    public Collection<T> getAllEntity(Class clazz) {
        TypedQuery<T> query = em.createQuery("SELECT c FROM " + clazz.getSimpleName() + " c", clazz);
        List<T> result = query.getResultList();
        return result;
    }

    @Override
    public T findEntityById(String idEntity) {
        T result = null;
        tx.begin();
        result = (T) em.find(Person.class, idEntity);
        if (result == null){
            result = (T) em.find(Contact.class, idEntity);
            if (result == null){
                result = (T) em.find(Folder.class, idEntity);
                if (result == null){
                    result = (T) em.find(Account.class, idEntity);
                    if (result == null){
                        result = (T) em.find(Letter.class, idEntity);
                        if (result == null){
                            result = (T) em.find(Attachment.class, idEntity);
                        }
                    }
                }
            }
        }
        tx.commit();
        return result;
    }

    @Override
    public void updateEntity(T entity) {
        addEntity(entity);
    }

    @Override
    public void removeEntity(T entity) {
        tx.begin();
        em.remove(entity);
        tx.commit();
    }
}
