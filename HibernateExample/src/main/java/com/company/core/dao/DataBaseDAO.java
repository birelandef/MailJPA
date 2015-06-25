package com.company.core.dao;

import com.company.api.DAO;
import com.company.core.entity.Entity;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.company.core.dao.DBHelper.*;

/** Common class for entities which defines methods for adding, removal and updating entities
 * @author Sophie
 * @date 25.03.2015.
 */
public abstract class DataBaseDAO<T extends Entity> implements DAO<T> {

    private static final Logger logger = Logger.getLogger(DataBaseDAO.class);

    @Override
    public void addEntity(T entity) {
        Connection connection = getConnection();
        PreparedStatement paramsStatement = null;
        try {
            paramsStatement = getAddEntityQuery(entity, connection);
            paramsStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't add record ", e);
        } finally {
            freeResources(connection, paramsStatement);
        }
    }


    @Override
    abstract public T findEntityById(String idEntity);

    @Override
    public void updateEntity(T entity) {
        removeEntity(entity);
        addEntity(entity);
    }

    @Override
    public void removeEntity(T entity) {
        Connection connection = getConnection();
        PreparedStatement paramsStatement = null;
        try {
            paramsStatement =  connection.prepareStatement("DELETE FROM " + entity.getClass().getSimpleName() + " WHERE id = ?");
            paramsStatement.setString(1, entity.getId());
            paramsStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete record ", e);
        } finally {
            freeResources(connection, paramsStatement);
        }
    }

    protected  abstract PreparedStatement getAddEntityQuery(T entity, Connection connection) throws SQLException;
}
