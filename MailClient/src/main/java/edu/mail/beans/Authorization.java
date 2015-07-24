package edu.mail.beans;
import edu.mail.core.dao.JPADAO;
import edu.mail.core.entities.Person;
import org.apache.log4j.Logger;

import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.Locale;

/**
 * @author Sophie
 * @date 06.07.2015.
 */

/**
 * Class for log in mailClient by special login and password(for this application).
 * ??
 * Probably if user forgot his password, he can log in his profile mailClient entering login and password from one of his
 * account.
 * Recovery - ???
 */
@Stateful
@Local(AuthorizationLocal.class)
public class Authorization implements AuthorizationLocal {

    private static final Logger logger = Logger.getLogger(Authorization.class);

    private Person authorizedPerson;

    public Authorization() {}

    public Person getAuthorizedPerson() {
        return authorizedPerson;
    }

    @Override
    public boolean logIn(String login, String password) throws RuntimeException{
        Locale.setDefault(Locale.ENGLISH);
        JPADAO<Person> personJPADAO = new JPADAO<>();
        String queryString = "SELECT p FROM Person p WHERE LOGIN='" + login + "'";
        String id = null;
        try {
            id = personJPADAO.executeQueryToFindEntityWithCondition(queryString);
            Person person = personJPADAO.findEntityById(id);
            if (person.getPassword().equals(password)){
                authorizedPerson = person;
                return true;
            }else{
                logger.error("Incorrect login/password");
                return false;
            }
        } catch (NoResultException e){
            logger.error("Incorrect login/password" , e);
            return false;
        } catch ( NonUniqueResultException e){
            logger.error("Exists two and more identical users" , e);
            throw new RuntimeException();
        }
    }

    @Override
    public void logOut() {
        authorizedPerson = null;
    }
}
