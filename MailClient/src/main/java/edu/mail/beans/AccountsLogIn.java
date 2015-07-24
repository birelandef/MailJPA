package edu.mail.beans;

import edu.mail.core.dao.JPADAO;
import edu.mail.core.entities.Account;
import edu.mail.core.entities.Person;
import org.apache.log4j.Logger;

import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Class for  log in and log out all accounts of user
 * @author Sophie
 * @date 07.07.2015.
 */
@Stateful
@Local(AccountsLogInLocal.class)

public class AccountsLogIn implements AccountsLogInLocal {

    private static final Logger logger = Logger.getLogger(AccountsLogIn.class);

    private Session session = null;
    private Store mailStore = null;

    public Session getSession() { return session; }
    public Store getMailStore() { return mailStore;}

    @Override
    public void logIn(String idAccount) {
        final Account account = (new JPADAO<Account>()).findEntityById(idAccount);
        final String user = account.getEmail();
        final String pass = account.getPassword();
        final String host = account.getIncomingMailServer();
        Properties properties = new Properties();
        try {
            properties.put("mail.debug", "true");
            properties.put("mail.store.protocol", "imaps");

            session = Session.getDefaultInstance(properties);
            mailStore = session.getStore();
            mailStore.connect(host, user, pass);

        } catch (NoSuchProviderException e) {
            logger.error("NoSuchProviderException", e);
        } catch (MessagingException e) {
            logger.error("Error to get store", e);
        }
    }

    public boolean isLogIn(){
        return mailStore.isConnected();
    }
    @Override
    public void logOut(String idAccount) {
        try {
            mailStore.close();
        } catch (MessagingException e) {
            logger.error("Error when closing the store");
        }
    }

    @Override
    public ArrayList<Account> getAccountsList(Person person) {
        return new ArrayList<Account>(person.getMailboxes().values());
    }
}
