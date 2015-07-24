package edu.mail.core.factory;

import edu.mail.api.Gender;
import edu.mail.core.entities.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The implementation of the interface {@link edu.mail.core.factory.EntityFactoryInterface}
 *
 * @author Sofia Ruban
 */
public class EntityFactoryImpl implements EntityFactoryInterface {

    private static final Logger log = LogManager.getLogger(EntityFactoryImpl.class);

    // Pattern Singleton
    private static EntityFactoryInterface INSTANCE = null;

    private EntityFactoryImpl() {}

    public static EntityFactoryInterface getInstance() {
        if (INSTANCE == null)
            INSTANCE = new EntityFactoryImpl();
        return INSTANCE;
    }

    @Override
    public Person createPerson(String login, String password, String name, String surname, Gender gender, Date birthday,
                               String country, String city, String info, Map<String, Account> mailboxes,
                               Map<String, Contact> contacts) {
        if (log.isTraceEnabled()) {
            log.trace("The call method createPerson() with parameters: {login = \"" + login + "\"}, {password = \"" + password
                    + "\"}, {name = \"" + name + "\"}, {surname = \"" + surname + "\"}, {gender = " + gender.getValue()
                    + "}, {birthday = " + (new SimpleDateFormat("dd MMM yyyy")).format(birthday) + "}, {country = \"" +
                    country + "\"}, {city = \"" + city + "\"}, {info = \"" + info + "\"}, {mailboxes = " +
                    mailboxes.toString() + "}, {contacts = " + contacts.toString() + "}");
        }
        return new Person(login, password, name, surname, gender, birthday, country, city, info, mailboxes, contacts);
    }

    @Override
    public Account createAccount(String email, String password, String outgoingMailServer, String incomingMailServer,
                                 Person idPerson) {
        if (log.isTraceEnabled()) {
            log.trace("The call method createAccount() with parameters: {email = \"" + email + "\"}, {password = \"" +
                    password + "\"}, {outgoingMailServer = \"" + outgoingMailServer + "\"}, {incomingMailServer = \"" +
                    incomingMailServer + "\"}, {idPerson = " + idPerson.getId() + "}");
        }
        return new Account(email, password, outgoingMailServer, incomingMailServer, idPerson);
    }

    @Override
    public Contact createContact(String email, String name, String surname) {
        if (log.isTraceEnabled()) {
            log.trace("The call method createContact() with parameters: {email = \"" + email + "\"}, {name = \"" + name
                    + "\"}, {surname = \"" + surname + "\"}");
        }
        return new Contact(email, name, surname);
    }

    @Override
    public Folder createFolder(Account idAccount, String name, Folder idParentFolder, Person idPerson,
                               boolean isSystemFolder, String description) {
        if (log.isTraceEnabled()) {
            log.trace("The call method createFolder() with parameters: {idAccount = " + idAccount.getId() + "}, {name = \""
                    + name + "\"}, {idParentFolder = " + idParentFolder.getId() + "}, {idPerson = " + idPerson.getId()
                    + "}, {isSystemFolder = " + isSystemFolder + "}, {description = \"" + description + "\"}");
        }
        return new Folder(idAccount, name, idParentFolder, idPerson, isSystemFolder, description);
    }

    @Override
    public Letter createLetter(Person idPerson, Folder idFolder, Account idAccount, boolean isSeen, String fromWhom,
                               List<String> toWhom, List<String> copy, String subject, String message,
                               List<Attachment> attachments, Date date) {
        if (log.isTraceEnabled()) {
            log.fatal("The call method createLetter() with parameters: {idPerson = " + idPerson.getId() + "}, {idFolder = "
                    + idFolder.getId() + "}, {idAccount = " + idAccount.getId() + "}, {isSeen = " + isSeen + "}, {fromWhom = \""
                    + fromWhom + "\"}, {toWhom = " + toWhom.toString() + "}, {copy = " + copy.toString() + "}, {subject = \""
                    + subject + "\"}, {message = \"" + message + "\"}, {attachments = " + attachments.toString() + "}, {date = \""
                    + (new SimpleDateFormat("dd MMM yyyy kk:mm:ss")).format(date) + "\"}");
        }
        return new Letter(idPerson, idFolder, idAccount, isSeen, fromWhom, toWhom, copy, subject, message, attachments,
                date);
    }

    @Override
    public Attachment createAttachment(String name, byte[] files, Letter idLetter, Folder idFolder, Account idAccount,
                                       Person idPerson) {
        if (log.isTraceEnabled()) {
            log.trace("The call method createAttachment() with parameters: {name = \"" + name + "\"}, {idLetter = "
                    + idLetter.getId() + "}, {idFolder = " + idFolder.getId() + "}, {idAccount = " + idAccount.getId()
                    + "}, {idPerson = " + idPerson.getId() + "}");
        }
        return new Attachment(name, files, idLetter, idFolder, idAccount, idPerson);
    }
}