package com.company.core.dao.JPADao;

import com.company.api.DAO;
import com.company.core.entity.Entity;
import com.mkyong.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Collection;
import java.util.Locale;

/**
 * @author Sophie
 * @date 24.06.2015.
 */
public class JPADaoImpl<T extends Entity> implements DAO<T>{

    private static Session session = null;

    static{
        Locale.setDefault(Locale.ENGLISH);
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public void addEntity(T entity) {
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
    }

    @Override
    public Collection<T> getAllEntity() {
        return null;
    }

    @Override
    public T findEntityById(String idEntity) {
        return null;
    }

    @Override
    public void updateEntity(T entity) {

    }

    @Override
    public void removeEntity(T entity) {

    }
}
