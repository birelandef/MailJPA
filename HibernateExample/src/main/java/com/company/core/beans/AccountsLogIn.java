package com.company.core.beans;

import com.company.core.dao.JPADAO;
import com.company.core.entity.Account;
import com.company.core.entity.Person;
import org.apache.log4j.Logger;

import javax.ejb.Local;
import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.*;

/**
 * @author Sophie
 * @date 07.07.2015.
 */
@Stateful
@Local(AccountsLogInLocal.class)

public class AccountsLogIn implements AccountsLogInLocal {

    private static final Logger logger = Logger.getLogger(AccountsLogIn.class);
    private Session session = null;
    private Store mailStore = null;

    @Override
    public void logIn(String idAccount) {
        final Account account = (new JPADAO<Account>()).findEntityById(idAccount);
        final String user = account.getEmail();
        final String pass = account.getPassword();
        System.out.println(pass);
        final String host = account.getIncomingMailServer();
        Properties properties = new Properties();
        try {
            properties.put("mail.debug", "true");
            properties.put("mail.store.protocol", "imaps");

            session = Session.getDefaultInstance(properties);
            mailStore = session.getStore();
            mailStore.connect(host, user, pass);

//            Folder inbox = mailStore.getFolder("INBOX");
//
//            inbox.open(Folder.READ_ONLY);
//            Message[] messages = inbox.getMessages();
//            for (Message m: messages){
//                System.out.println(m.getSubject());
//            }
//            inbox.close(true);
//            mailStore.close();
        } catch (NoSuchProviderException e) {
            logger.error("NoSuchProviderException", e);
        } catch (MessagingException e) {
            logger.error("Error to get store", e);
        }
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
    public List<Account> getAccountsList(Person person) {
        return new ArrayList<Account>(person.getMailboxes().values());
    }
}
