package com.company.core.factory;

import com.company.api.EntityFactoryInterface;
import com.company.api.Gender;
import com.company.core.entity.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * TODO javadoc
 *
 * @author Sofia Ruban
 */
public class EntityFactoryImpl implements EntityFactoryInterface {

    //Pattern Singleton
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
        return new Person(login, password, name, surname, gender, birthday, country, city, info, mailboxes, contacts);
    }

    @Override
    public Account createAccount(String email, String password, String outgoingMailServer, String incomingMailServer, String idPerson) {
        return new Account(email, password, outgoingMailServer, incomingMailServer, idPerson);
    }

    @Override
    public Contact createContact(String email, String name, String surname) {
        return new Contact(email, name, surname);
    }

    @Override
    public Folder createFolder(String idAccount, String name, String idParentFolder, String idPerson,
                               boolean isSystemFolder, String description) {
        return new Folder(idAccount, name, idParentFolder, idPerson, isSystemFolder, description);
    }

    @Override
    public Letter createLetter(String idPerson, String idFolder, String idAccount, boolean isSeen, String fromWhom, List<String> toWhom,
                                           List<String> copy, String subject, String message, List<Attachment> attachments, Date date){
        return new Letter(idPerson,idFolder, idAccount, isSeen, fromWhom, toWhom,copy, subject, message, attachments, date);
    }

    @Override
    public Attachment createAttachment(String name, byte[] files, String idLetter, String idFolder, String idAccount, String idPerson) {
        return new Attachment(name, files, idLetter, idFolder, idAccount, idPerson);
    }
}
