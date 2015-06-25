package com.company.core.dao.EntityDBDAO;

import com.company.core.dao.DBHelper;
import com.company.core.dao.DataBaseDAO;
import com.company.core.entity.Account;
import com.company.core.factory.EntityFactoryImpl;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static com.company.core.dao.DBHelper.*;

/** Class for special operation with Accounts such as getAllEntity and addEntity
 * @author Sophie
 * @date 14.05.2015.
 */
public class AccountDAO extends DataBaseDAO<Account> {

    private static Logger logger = Logger.getLogger(AttachmentDAO.class);

    @Override
    protected PreparedStatement getAddEntityQuery(Account entity, Connection connection) throws SQLException {
        PreparedStatement paramsStatement = connection.prepareStatement("INSERT INTO ACCOUNT VALUES (?,?,?,?,?,?)");
        paramsStatement.setString(1, entity.getId());
        paramsStatement.setString(2, entity.getEmail());
        paramsStatement.setString(3, entity.getPassword());
        paramsStatement.setString(4, entity.getOutgoingMailServer());
        paramsStatement.setString(5, entity.getIncomingMailServer());
        paramsStatement.setString(6, entity.getIdPerson());
        return paramsStatement;
    }
    @Override
    public Account findEntityById(String id){
        Connection connection = DBHelper.getConnection();
        PreparedStatement paramsStatement = null;
        ResultSet resultSet = null;
        try {
            paramsStatement = connection.prepareStatement("SELECT *  FROM ACCOUNT WHERE ID='" + id + "'");
            resultSet = paramsStatement.executeQuery();
            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String out = resultSet.getString("outgoingMailServer");
                String in = resultSet.getString("incomingMailServer");
                String idPerson = resultSet.getString("idPerson");
                EntityFactoryImpl factory = (EntityFactoryImpl) EntityFactoryImpl.getInstance();
                Account account = factory.createAccount(email, password, out, in, idPerson);
                account.setId(resultSet.getString("id"));
                return account;
            }
        } catch (SQLException e){
            logger.error("Can't read entity", e);
            return null;
        }finally {
            freeResources(connection, paramsStatement, resultSet);
        }
        return null;
    }

    @Override
    public Collection<Account> getAllEntity() {
        Collection<Account> collection = new ArrayList<Account>();
        Connection connection = getConnection();
        PreparedStatement paramsStatement = null;
        ResultSet resultSet = null;
        try {
            paramsStatement = connection.prepareStatement("SELECT *  FROM ACCOUNT");
            resultSet = paramsStatement.executeQuery();
            while (resultSet.next()) {
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String out = resultSet.getString("outgoingMailServer");
                String in = resultSet.getString("incomingMailServer");
                String idPerson = resultSet.getString("idPerson");

                EntityFactoryImpl factory = (EntityFactoryImpl) EntityFactoryImpl.getInstance();
                Account account = factory.createAccount(email, password, out, in, idPerson);
                account.setId(resultSet.getString("id"));
                collection.add(account);
            }
        } catch (SQLException e) {
            logger.error("Can't delete record ", e);
        } finally {
            freeResources(connection,paramsStatement,resultSet);
        }
        return collection;
    }
}
