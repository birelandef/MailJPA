package edu.mail.core.dao;

import com.company.core.api.Constants;
import edu.mail.core.entities.*;
import edu.mail.core.entities.Entity;
import org.apache.log4j.Logger;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

/**
 * Class for providing transactions with entities's class
 * @author Sophie
 * @date 27.06.2015.
 */
public class JPADAO<T extends Entity> implements DAO<T> {


    private static final Logger logger = Logger.getLogger(JPADAO.class);

    private EntityManagerFactory emf = null;
    private EntityManager em = null;
    private EntityTransaction tx = null;

    public JPADAO() {
        Locale.setDefault(Locale.ENGLISH);
        emf = Persistence.createEntityManagerFactory(Constants.unitPersistence);
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

    public boolean isContains(T entity){
        return (this.findEntityById(entity.getId()) != null ? true : false);
    }

    public String executeQueryToFindEntityWithCondition(String queryString) throws NoResultException, NonUniqueResultException {
        TypedQuery<Entity> query = em.createQuery(queryString, Entity.class);
        Entity singleResult = query.getSingleResult();
        return  singleResult.getId();
    }

//
//    public T findEntityByField(Class entityName, String fieldName, String valueOfFild){
//        CriteriaBuilder builder = em.getCriteriaBuilder();
//        CriteriaQuery<T> criteriaQuery = builder.createQuery(entityName);
//        Root<T> c = criteriaQuery.from(entityName);
//        criteriaQuery.select(c). where(builder.equal( c.get(fieldName) , valueOfFild));
//        Query query = em.createQuery( criteriaQuery);
//        //TODO ����� findById
//        List<T> entities = query.getResultList();
//        //TODO ��� �������� ���������� �����������
//        return entities.get(0); //(entities.get(0) != null ? entities.get(0) : null);
//    }
}
