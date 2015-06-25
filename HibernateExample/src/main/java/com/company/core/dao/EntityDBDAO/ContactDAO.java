package com.company.core.dao.EntityDBDAO;

import com.company.core.dao.DBHelper;
import com.company.core.dao.DataBaseDAO;
import com.company.core.entity.Contact;
import com.company.core.factory.EntityFactoryImpl;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import static com.company.core.dao.DBHelper.*;

/**
 * Class for special operation with Contacts such as getAllEntity and addEntity
 *
 * @author Sophie
 * @date 14.05.2015.
 */
public class ContactDAO extends DataBaseDAO<Contact>{

    private static final Logger logger = Logger.getLogger(ContactDAO.class);

    @Override
    public Collection<Contact> getAllEntity() {
        Collection<Contact> collection = new ArrayList<Contact>();
        Connection connection = getConnection();
        PreparedStatement  paramsStatement = null;
        ResultSet resultSet = null;
        try {
            paramsStatement = connection.prepareStatement("SELECT * FROM CONTACT");
            resultSet = paramsStatement.executeQuery();
            while (resultSet.next()) {
                String email = resultSet.getString("email");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");

                EntityFactoryImpl factory = (EntityFactoryImpl) EntityFactoryImpl.getInstance();
                Contact contact = factory.createContact(email, name, surname);
                contact.setId(resultSet.getString("id"));
                collection.add(contact);
            }
        } catch (SQLException e) {
            logger.error("Can't read record ", e);
        } finally {
            freeResources(connection, paramsStatement, resultSet);
        }
        return collection;
    }

    @Override
    public Contact findEntityById(String idEntity) {
        Connection connection = DBHelper.getConnection();
        PreparedStatement paramsStatement = null;
        ResultSet resultSet = null;
        try {
            paramsStatement = connection.prepareStatement("SELECT * FROM CONTACT WHERE ID='" + idEntity + "'");
            resultSet = paramsStatement.executeQuery();
            if (resultSet.next()) {

                String email = resultSet.getString("email");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");

                EntityFactoryImpl factory = (EntityFactoryImpl) EntityFactoryImpl.getInstance();
                Contact contact = factory.createContact(email, name, surname);
                contact.setId(resultSet.getString("id"));
                return  contact;
            }
        } catch (SQLException e) {
            logger.error("Can't read entity", e);
            return null;
        } finally {
            freeResources(connection, paramsStatement, resultSet);
        }
        return null;
    }

    protected PreparedStatement getAddEntityQuery(Contact entity, Connection connection) throws SQLException {
            PreparedStatement paramsStatement = connection.prepareStatement("INSERT INTO CONTACT VALUES (?,?,?,?)");
            paramsStatement.setString(1, entity.getId());
            paramsStatement.setString(2, entity.getEmail());
            paramsStatement.setString(3, entity.getName());
            paramsStatement.setString(4, entity.getSurname());
            return paramsStatement;
    }
}
