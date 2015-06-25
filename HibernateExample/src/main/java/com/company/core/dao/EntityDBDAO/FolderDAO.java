package com.company.core.dao.EntityDBDAO;

import com.company.core.dao.DataBaseDAO;
import com.company.core.entity.Folder;
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
 * Class for special operation with Folders such as getAllEntity and addEntity
 *
 * @author Sophie
 * @date 15.05.2015.
 */
public class FolderDAO extends DataBaseDAO<Folder> {

    private static Logger logger = Logger.getLogger(FolderDAO.class);

    @Override
    public Folder findEntityById(String idEntity) {
        Connection connection = getConnection();
        PreparedStatement paramsStatement = null;
        ResultSet resultSet = null;
        try {
            paramsStatement = connection.prepareStatement("SELECT *  FROM FOLDER WHERE ID='" + idEntity + "'");
            resultSet = paramsStatement.executeQuery();
            if (resultSet.next()) {

                String idAccount = resultSet.getString("idAccount");
                String name = resultSet.getString("name");
                String idParentFolder = resultSet.getString("idParentFolder");
                String idPerson = resultSet.getString("idPerson");
                Boolean isSystemFolder = resultSet.getBoolean("isSystemFolder");
                String description = resultSet.getString("description");

                EntityFactoryImpl factory = (EntityFactoryImpl) EntityFactoryImpl.getInstance();
                Folder folder = factory.createFolder(idAccount, name,
                        idParentFolder, idPerson,isSystemFolder,
                        description);
                folder.setId(resultSet.getString("id"));
                return folder;
            }
        } catch (SQLException e) {
            logger.error("Can't read entity", e);
            return null;
        } finally {
            freeResources(connection,paramsStatement,resultSet);
        }
        return null;
    }

    @Override
    protected PreparedStatement getAddEntityQuery(Folder entity, Connection connection) throws SQLException {
        PreparedStatement paramsStatement = connection.prepareStatement("INSERT INTO FOLDER VALUES (?,?,?,?,?,?,?)");
        paramsStatement.setString(1, entity.getId());
        paramsStatement.setString(2, entity.getName());
        paramsStatement.setString(3, entity.getIdParentFolder());
        paramsStatement.setBoolean(4, entity.isSystemFolder());
        paramsStatement.setString(5, entity.getDescription());
        paramsStatement.setString(6, entity.getIdAccount());
        paramsStatement.setString(7, entity.getIdPerson());
        return paramsStatement;
    }

    @Override
    public Collection<Folder> getAllEntity() {
        Collection<Folder> collection = new ArrayList<Folder>();
        Connection connection = getConnection();
        PreparedStatement paramsStatement = null;
        ResultSet resultSet = null;
        try {
            paramsStatement = connection.prepareStatement("SELECT *  FROM FOLDER");
            resultSet = paramsStatement.executeQuery();
            while (resultSet.next()) {

                String idAccount = resultSet.getString("idAccount");
                String name = resultSet.getString("name");
                String idParentFolder = resultSet.getString("idParentFolder");
                String idPerson = resultSet.getString("idPerson");
                Boolean isSystemFolder = resultSet.getBoolean("isSystemFolder");
                String description = resultSet.getString("description");

                EntityFactoryImpl factory = (EntityFactoryImpl) EntityFactoryImpl.getInstance();
                Folder folder = factory.createFolder(idAccount, name,
                        idParentFolder, idPerson,isSystemFolder,
                        description);
                folder.setId(resultSet.getString("id"));
                collection.add(folder);
            }
        } catch (SQLException e) {
            logger.error("Can't delete record ", e);
        } finally {
            freeResources(connection,paramsStatement,resultSet);
        }
        return collection;
    }
}
