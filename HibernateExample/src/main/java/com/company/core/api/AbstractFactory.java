package com.company.core.api;

import com.company.core.entity.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Interface allows to create different entities
 *
 * Created by Sophie on 18.03.2015.
 */
public interface AbstractFactory {

    /**
     *
     *
     * @param login
     * @param password
     * @param incomingServer
     * @param incomingServerPort
     * @param outgoingServer
     * @param outgoingServerPort
     * @return
     */
    public Account createAccount(String login, String password, String incomingServer, int incomingServerPort,
                                 String outgoingServer, int outgoingServerPort);

    /**
     *
     * @param name
     * @param size
     * @param file
     * @return
     */
    public Attachment createAttachment(String name, int size, byte[] file);

    /**
     *
     * @param email
     * @param firstName
     * @param secondName
     * @return
     */
    public Contact createContact(String email, String firstName, String secondName);

    /**
     *
     * @param name
     * @param idPerson
     * @param idParent
     * @param idAccount
     * @param description
     * @return
     */
    public Folder createFolder(String name, BigInteger idPerson, BigInteger idParent, BigInteger idAccount,
                               String description);

    /**
     *
     * @param dateFrom
     * @param emailRecipient
     * @param listSender
     * @param subject
     * @param carbonCopy
     * @param blindCarbonCopy
     * @param message
     * @param listAttachment
     * @param idFolder
     * @param isRead
     * @return
     */
    public Letter createLetter(Date dateFrom, String emailRecipient, List<String> listSender, String subject, List<String> carbonCopy,
                               List<String> blindCarbonCopy, String message, List<Attachment> listAttachment, String idFolder,
                               String idPerson, String idAccount,boolean isRead);

    /**
     *
     * @param firstName
     * @param secondName
     * @param birthDay
     * @param gender
     * @param accounts
     * @param contacts
     * @return
     */
    public Person createPerson(String firstName, String secondName, GregorianCalendar birthDay, String gender,
                               List<Account> accounts, List<Contact> contacts);

//    public <T extends Generator> T create();

}
