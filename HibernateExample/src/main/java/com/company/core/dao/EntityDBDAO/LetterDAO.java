package com.company.core.dao.EntityDBDAO;

import com.company.core.dao.DataBaseDAO;
import com.company.core.entity.Attachment;
import com.company.core.entity.Entity;
import com.company.core.entity.Letter;
import com.company.core.factory.EntityFactoryImpl;
import org.apache.log4j.Logger;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import static com.company.core.dao.DBHelper.freeResources;
import static com.company.core.dao.DBHelper.getConnection;

/**Class for special operation with Letters such as getAllEntity and addEntity
 * @author Sophie
 * @date 15.05.2015.
 */

public class LetterDAO extends DataBaseDAO<Letter> {

    private static Logger logger = Logger.getLogger(AttachmentDAO.class);

    @Override
    public void addEntity(Letter entity) {
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
    public Letter findEntityById(String idEntity) {
        Connection connection = getConnection();
        PreparedStatement paramsStatement = null;
        ResultSet resultSet = null;
        EntityFactoryImpl factory = (EntityFactoryImpl) EntityFactoryImpl.getInstance();
        try {
            paramsStatement = connection.prepareStatement("SELECT *  FROM LETTER WHERE ID='" + idEntity + "'");
            resultSet = paramsStatement.executeQuery();
            if (resultSet.next()) {

                List<String> toWhom = convertString2List(resultSet.getString("TOWHOM"));
                List<String> copy =  convertString2List(resultSet.getString("COPY"));
                List<Attachment> attachments = getAttachments(connection,factory, resultSet.getString("id"));

                String idPerson = resultSet.getString("idPerson");
                String idFolder = resultSet.getString("idFolder");
                String idAccount = resultSet.getString("idAccount");
                Boolean isSeen = resultSet.getBoolean("isSeen");
                String fromWhom = resultSet.getString("fromWhom");
                String subject = resultSet.getString("subject");
                String message = resultSet.getString("message");
                Date date = resultSet.getDate("DATETO");

                Letter letter = factory.createLetter(idPerson, idFolder, idAccount, isSeen, fromWhom, toWhom,
                        copy, subject, message, attachments, date);
                letter.setId(resultSet.getString("id"));
                return letter;
            }
        } catch (SQLException e) {
            logger.error("Can't delete record ", e);
            return null;
        } finally {
            freeResources(connection, paramsStatement, resultSet);
        }
        return null;
    }

    @Override
    protected PreparedStatement getAddEntityQuery(Letter entity, Connection connection) throws SQLException {
        PreparedStatement paramsStatement = connection.prepareStatement("INSERT INTO LETTER VALUES (?,?,?,?,?,?,?,?,?,?,?)");
        paramsStatement.setString(1, entity.getId());
        paramsStatement.setBoolean(2, entity.isSeen());
        paramsStatement.setString(3, entity.getSubject());
        paramsStatement.setString(4, entity.getMessage());
        paramsStatement.setDate(5, entity.getDate());
        paramsStatement.setString(6, entity.getIdFolder());
        paramsStatement.setString(7, entity.getIdAccount());
        paramsStatement.setString(8, entity.getIdPerson());
        paramsStatement.setString(9, entity.getFromWhom());
        paramsStatement.setString(10, convertList2String(entity.getToWhom()));
        paramsStatement.setString(11, convertList2String(entity.getCopy()));
        return paramsStatement;
    }

    private static String convertList2String(List<String> list){
        StringBuffer listToString = new StringBuffer();
        for (String content: list){
            listToString.append(content);
            listToString.append(", ");
        }
        return listToString.toString();
    }

    @Override
    public Collection<Letter> getAllEntity() {
        Collection<Letter> collection = new ArrayList<Letter>();
        Connection connection = getConnection();
        PreparedStatement paramsStatement = null;
        ResultSet resultSet = null;
        EntityFactoryImpl factory = (EntityFactoryImpl) EntityFactoryImpl.getInstance();
        try {
            paramsStatement = connection.prepareStatement("SELECT *  FROM LETTER");
            resultSet = paramsStatement.executeQuery();
            while (resultSet.next()) {

                List<String> toWhom = convertString2List(resultSet.getString("TOWHOM"));
                List<String> copy =  convertString2List(resultSet.getString("COPY"));
                List<Attachment> attachments = getAttachments(connection,factory, resultSet.getString("id"));

                String idPerson = resultSet.getString("idPerson");
                String idFolder = resultSet.getString("idFolder");
                String idAccount = resultSet.getString("idAccount");
                Boolean isSeen = resultSet.getBoolean("isSeen");
                String fromWhom = resultSet.getString("fromWhom");
                String subject = resultSet.getString("subject");
                String message = resultSet.getString("message");
                Date date = resultSet.getDate("DATETO");

                Letter letter = factory.createLetter(idPerson, idFolder, idAccount, isSeen, fromWhom, toWhom,
                        copy, subject, message, attachments, date);
                letter.setId(resultSet.getString("id"));
                collection.add(letter);
            }
        } catch (SQLException e) {
            logger.error("Can't delete record ", e);
            e.printStackTrace();
        } finally {
            freeResources(connection, paramsStatement, resultSet);
        }
        return collection;
    }

    private List<String> convertString2List(String listAsString){
        List<String> list = new ArrayList<String>();
        if (listAsString != null){
            String [] items = listAsString.split(",");
            list = Arrays.asList(items);
        }
        return  list;
    }

    private List<Attachment> getAttachments(Connection connection, EntityFactoryImpl factory, String idLetter) throws SQLException {
        List<Attachment> attachmentList = new ArrayList<Attachment>();
        PreparedStatement paramsStatement = null;
        ResultSet resultSet = null;
        try {
            paramsStatement = connection.prepareStatement("SELECT *  FROM ATTACHMENT WHERE idLetter ='" + idLetter + "'");
            resultSet = paramsStatement.executeQuery();
            while (resultSet.next()) {
                Blob files = resultSet.getBlob("FILES");
                String name = resultSet.getString("NAME");
                String idFolder = resultSet.getString("IDFOLDER");
                String idAccount = resultSet.getString("IDACCOUNT");
                String idPerson = resultSet.getString("IDPERSON");
                Attachment attachment = factory.createAttachment(name, files.getBytes(0, (int) files.length()),idLetter, idFolder, idAccount, idPerson);
                attachmentList.add(attachment);
            }
        } finally {
            freeResources(paramsStatement,resultSet);
        }
        return attachmentList;
    }
}
