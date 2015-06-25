package com.company.core.dao.EntityDBDAO;

import com.company.api.Gender;
import com.company.core.dao.DataBaseDAO;
import com.company.core.entity.Account;
import com.company.core.entity.Contact;
import com.company.core.entity.Person;
import com.company.core.factory.EntityFactoryImpl;
import org.apache.log4j.Logger;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import static com.company.core.dao.DBHelper.freeResources;
import static com.company.core.dao.DBHelper.getConnection;

/**Class for special operation with Persons such as getAllEntity and addEntity
 *
 * @author Sophie
 * @date 14.05.2015.
 */
public class PersonDAO  extends DataBaseDAO<Person>{

    private static Logger logger = Logger.getLogger(AttachmentDAO.class);


    public void addEntity(Person entity) {
        Connection connection = getConnection();
        PreparedStatement paramsStatement = null;
        try {
            paramsStatement = getAddEntityQuery(entity, connection);
            paramsStatement.executeUpdate();
            insertContacts(connection, entity.getId(), entity.getContacts());
            insertAccounts(connection, entity.getId(), entity.getMailboxes());
            ;
        } catch (SQLException e) {
            logger.error("Can't add record ", e);
        } finally {
            freeResources(connection, paramsStatement);
        }
    }

    @Override
    public Person findEntityById(String idEntity) {
        return null;
    }

    @Override
    protected PreparedStatement getAddEntityQuery(Person entity, Connection connection) throws SQLException {
        PreparedStatement paramsStatement = connection.prepareStatement("INSERT INTO PERSON VALUES (?,?,?,?,?,?,?,?,?,?)");
        paramsStatement.setString(1, entity.getId());
        paramsStatement.setString(2, entity.getLogin());
        paramsStatement.setString(3, entity.getPassword());
        paramsStatement.setString(4, entity.getName());
        paramsStatement.setString(5, entity.getSurname());
        paramsStatement.setString(6, entity.getGender().toString());
        paramsStatement.setString(7, entity.getCountry());
        paramsStatement.setString(8, entity.getCity());
        paramsStatement.setString(9, entity.getInfo());
        paramsStatement.setDate(10,  entity.getBirthday());
        return paramsStatement;
    }


    private static void insertContacts(Connection connection,String idPerson, Map<String, Contact> map) throws SQLException {
        ContactDAO contactDAO = new ContactDAO();
        for (Map.Entry<String, Contact> entry : map.entrySet()){
            //проверка наличия такого контакта в базе
            if (contactDAO.findEntityById(entry.getKey()) == null){
                contactDAO.addEntity(entry.getValue());
            }
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PERSON_CONTACT VALUES (?,?)");
            preparedStatement.setString(1, idPerson);
            preparedStatement.setString(2,entry.getValue().getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
    }

    private static void insertAccounts(Connection connection,String idPerson,Map<String, Account> map) throws SQLException {
        AccountDAO accountDAO = new AccountDAO();
        for (Map.Entry<String, Account> entry : map.entrySet()){
            //проверка наличия такого аккаунта в базе
            if (accountDAO.findEntityById(entry.getKey()) == null){
                accountDAO.addEntity(entry.getValue());
            }
        }
    }

    @Override
    public Collection<Person> getAllEntity() {
        Collection<Person> collection = new ArrayList<Person>();
        Connection connection = getConnection();
        PreparedStatement paramsStatement = null;
        ResultSet resultSet = null;
        EntityFactoryImpl factory = (EntityFactoryImpl) EntityFactoryImpl.getInstance();
        try {
            paramsStatement = connection.prepareStatement("SELECT *  FROM PERSON");
            resultSet = paramsStatement.executeQuery();
           while (resultSet.next()) {
               String id = resultSet.getString("id");
               String login = resultSet.getString("login");
               String password = resultSet.getString("password");
               String name = resultSet.getString("Name");
               String surname = resultSet.getString("Surname");
               Gender gender = (resultSet.getString("Gender").equals("MAN") ? Gender.MAN : Gender.WOMAN);
               Date birth = resultSet.getDate("Birthday");
               String country = resultSet.getString("Country");
               String city = resultSet.getString("City");
               String info = resultSet.getString("Info");
               Person person = factory.createPerson(login, password,name, surname, gender, birth, country, city, info,
                       getAccounts(connection, id, factory), getContacts(connection, id, factory));
               person.setId(id);
               collection.add(person);
            }
        } catch (SQLException e) {
            logger.error("Can't delete record ", e);
        } finally {
            freeResources(connection,paramsStatement, resultSet);
        }
        return collection;
    }

    private static HashMap<String,Account> getAccounts(Connection connection, String idPerson, EntityFactoryImpl factory) throws SQLException {
        HashMap<String, Account> acccountsHash = new HashMap<String, Account>();
        PreparedStatement statementAccount = null;
        ResultSet accounts = null;
        try {
            statementAccount = connection.prepareStatement("SELECT *  FROM ACCOUNT WHERE IDPERSON='" + idPerson + "'");
            accounts = statementAccount.executeQuery();
            while(accounts.next()){
                Account account = factory.createAccount(accounts.getString("email"), accounts.getString("password"),
                        accounts.getString("outgoingMailServer"),accounts.getString("incomingMailServer"),
                        accounts.getString("idPerson"));
                account.setId(accounts.getString("ID"));
                acccountsHash.put(account.getId(), account);
            }
        } finally {
            freeResources(statementAccount,accounts);
        }
        return  acccountsHash;
    }

    private static HashMap<String, Contact> getContacts(Connection connection, String idPerson, EntityFactoryImpl factory) throws SQLException {
        HashMap<String, Contact> contactsHash = new HashMap<String, Contact>();
        PreparedStatement statementContacts = null;
        ResultSet contacts = null;
        try {
            statementContacts = connection.prepareStatement("SELECT *  FROM PERSON_CONTACT JOIN CONTACT ON PERSON_CONTACT.IDCONTACT=CONTACT.ID WHERE IDPERSON='" + idPerson + "'");
            contacts = statementContacts.executeQuery();
            while (contacts.next()) {
                Contact contact = factory.createContact(contacts.getString("email"), contacts.getString("name"), contacts.getString("surname"));
                contact.setId(contacts.getString("ID"));
                contactsHash.put(contact.getId(), contact);
            }
        } catch (SQLException e){
            logger.error("Don't exists contacts", e);
            return  contactsHash;
        } finally {
            freeResources(statementContacts, contacts);
        }
        return  contactsHash;
    }

}
