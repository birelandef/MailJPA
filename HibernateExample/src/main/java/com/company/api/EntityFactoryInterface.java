package com.company.api;

import com.company.core.entity.*;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * TODO javadoc
 *
 * @author Sofia Ruban
 */
public interface EntityFactoryInterface {

    /**
     *
     * @param login
     * @param password
     * @param name
     * @param surname
     * @param gender
     * @param birthday
     * @param country
     * @param city
     * @param info
     * @param mailboxes
     * @param contacts
     * @return
     */
    Person createPerson(String login, String password, String name, String surname, Gender gender, Date birthday,
                        String country, String city, String info, Map<String, Account> mailboxes,
                        Map<String, Contact> contacts);

    /**
     *
     * @param email
     * @param password
     * @param outgoingMailServer
     * @param incomingMailServer
     * @param idPerson
     * @return
     */
    Account createAccount(String email, String password, String outgoingMailServer, String incomingMailServer, String idPerson);

    Contact createContact(String email, String name, String surname);

    /**
     *
     * @param idAccount
     * @param name
     * @param idParentFolder
     * @param idPerson
     * @param isSystemFolder
     * @param description
     * @return
     */
    Folder createFolder(String idAccount, String name, String idParentFolder, String idPerson, boolean isSystemFolder,
                        String description);

    /**
     *
     * @param idPerson
     * @param idFolder
     * @param idAccount
     * @param isSeen
     * @param fromWhom
     * @param toWhom
     * @param copy
     * @param subject
     * @param message
     * @param attachments
     * @param date
     * @return
     */
    Letter createLetter(String idPerson, String idFolder, String idAccount, boolean isSeen, String fromWhom, List<String> toWhom,
                        List<String> copy, String subject, String message, List<Attachment> attachments, Date date);

    /**
     *
     * @param name
     * @param files
     * @param idLetter
     * @param idFolder
     * @param idAccount
     * @param idPerson
     * @return
     */
    Attachment createAttachment(String name, byte[] files, String idLetter, String idFolder, String idAccount, String idPerson);
}
