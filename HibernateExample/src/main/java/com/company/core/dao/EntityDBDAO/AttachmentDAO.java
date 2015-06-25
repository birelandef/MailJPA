package com.company.core.dao.EntityDBDAO;

import com.company.core.dao.DBHelper;
import com.company.core.dao.DataBaseDAO;
import com.company.core.entity.Attachment;
import com.company.core.factory.EntityFactoryImpl;
import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import static com.company.core.dao.DBHelper.*;

/**Class for special operation with Attachments such as getAllEntity and addEntity
 * @author Sophie
 * @date 16.05.2015.
 */
public class AttachmentDAO extends DataBaseDAO<Attachment> {

    private static Logger logger = Logger.getLogger(AttachmentDAO.class);

    @Override
    public Attachment findEntityById(String idEntity) {
        Connection connection = DBHelper.getConnection();
        PreparedStatement paramsStatement = null;
        ResultSet resultSet = null;
        try{
            paramsStatement = connection.prepareStatement("SELECT *  FROM ATTACHMENT");
            resultSet = paramsStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                Blob files = resultSet.getBlob("files");
                byte[] content = files.getBytes(1, (int) files.length());
                String idLetter = resultSet.getString("IDLETTER");
                String idFolder = resultSet.getString("IDFOLDER");
                String idAccount = resultSet.getString("IDACCOUNT");
                String idPerson = resultSet.getString("IDPERSON");
                EntityFactoryImpl factory = (EntityFactoryImpl) EntityFactoryImpl.getInstance();
                Attachment attachment = factory.createAttachment(name, content, idLetter, idFolder, idAccount, idPerson);
                attachment.setId(resultSet.getString("id"));
                return attachment;
            }
        } catch (SQLException e) {
            logger.error("Can't read entity", e);
            return null;
        } finally {
            freeResources(connection, paramsStatement, resultSet);
        }
        return null;
    }

    @Override
    protected PreparedStatement getAddEntityQuery(Attachment entity, Connection connection) throws SQLException {
        PreparedStatement paramsStatement = connection.prepareStatement("INSERT INTO ATTACHMENT VALUES (?,?,?,?,?,?,?)");
        paramsStatement.setString(1, entity.getId());
        paramsStatement.setString(2, entity.getName());
        //TODO проверить
        paramsStatement.setBlob(3, new ByteArrayInputStream(entity.getFiles()));
        paramsStatement.setString(4, entity.getIdLetter());
        paramsStatement.setString(5, entity.getIdFolder());
        paramsStatement.setString(6, entity.getIdAccount());
        paramsStatement.setString(7, entity.getIdPerson());
        return paramsStatement;
    }

    @Override
    public Collection<Attachment> getAllEntity() {
        Collection<Attachment> collection = new ArrayList<Attachment>();
        Connection connection = getConnection();
        PreparedStatement paramsStatement = null;
        ResultSet resultSet = null;
        try {
            paramsStatement = connection.prepareStatement("SELECT *  FROM ATTACHMENT");
            resultSet = paramsStatement.executeQuery();
            while (resultSet.next()) {

                String name = resultSet.getString("name");
                Blob files = resultSet.getBlob("files");
                byte[] content = files.getBytes(1, (int) files.length());
                String idLetter = resultSet.getString("IDLETTER");
                String idFolder = resultSet.getString("IDFOLDER");
                String idAccount = resultSet.getString("IDACCOUNT");
                String idPerson = resultSet.getString("IDPERSON");

                EntityFactoryImpl factory = (EntityFactoryImpl) EntityFactoryImpl.getInstance();
                Attachment attachment = factory.createAttachment(name, content, idLetter, idFolder, idAccount,idPerson);
                attachment.setId(resultSet.getString("id"));
                collection.add(attachment);
            }
        } catch (SQLException e) {
            logger.error("Can't delete record ", e);
        } finally {
            freeResources(connection, paramsStatement, resultSet);
        }
        return collection;
    }
}
