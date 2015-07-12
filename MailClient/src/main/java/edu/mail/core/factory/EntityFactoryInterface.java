package edu.mail.core.factory;

import edu.mail.api.Gender;
import edu.mail.core.entities.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The interface contains methods to create entities application "Mail Client"
 *
 * @author Sofia Ruban
 */
public interface EntityFactoryInterface {

    /**
     * The method allows to create an entity {@link edu.mail.core.entities.Person}
     *
     * @param login     the login user from account in application Mail Client
     * @param password  the password user from  account in application Mail Client
     * @param name      real user name
     * @param surname   the user's last name
     * @param gender    the gender of the user
     * @param birthday  birthday user
     * @param country   the country in which the user lives
     * @param city      the city where the user lives
     * @param info      additional information about the user
     * @param mailboxes the list of accounts from mailbox user
     * @param contacts  the list of users who have already been sent letters
     * @return entity {@link edu.mail.core.entities.Person}
     */
    Person createPerson(String login, String password, String name, String surname, Gender gender, Date birthday,
                        String country, String city, String info, Map<String, Account> mailboxes,
                        Map<String, Contact> contacts);


    /**
     * The method allows to create an entity {@link edu.mail.core.entities.Account}
     *
     * @param email              e-mail address of the user's mailbox
     * @param password           the password from the user's mailbox
     * @param outgoingMailServer address outgoing mail server
     * @param incomingMailServer address incoming mail server
     * @param idPerson           the identifier of the user who owns the account in the mailbox
     * @return entity {@link edu.mail.core.entities.Account}
     */
    Account createAccount(String email, String password, String outgoingMailServer, String incomingMailServer, Person idPerson);

    /**
     * The method allows to create an entity {@link edu.mail.core.entities.Contact}
     *
     * @param email   the mailbox address of the user who sent the letter
     * @param name    the name of the user who sent the letter
     * @param surname the surname of the user who sent the letter
     * @return entity {@link edu.mail.core.entities.Contact}
     */
    Contact createContact(String email, String name, String surname);

    /**
     * The method allows to create an entity {@link edu.mail.core.entities.Folder}
     *
     * @param idAccount      the identifier account of the mailbox user  which contains this folder
     * @param name           the name of the folder
     * @param idParentFolder the identifier of the parent folder
     * @param idPerson       the identifier of the user account in application the Mail Client that owns the folder
     * @param isSystemFolder mark about system folder
     * @param description    description of folder
     * @return entity {@link edu.mail.core.entities.Folder}
     */
    Folder createFolder(Account idAccount, String name, Folder idParentFolder, Person idPerson, boolean isSystemFolder,
                        String description);

    /**
     * The method allows to create an entity {@link edu.mail.core.entities.Letter}
     *
     * @param idPerson    the identifier user that owns the letter
     * @param idFolder    the identifier of the folder containing the letter
     * @param isSeen      mark view letter
     * @param fromWhom    email address of the mailbox from which was sent or sent a letter
     * @param toWhom      the list of electronic addresses of recipients letter
     * @param copy        the list of electronic addresses of recipients of copies of the letter
     * @param subject     subject letter
     * @param message     the body letter
     * @param attachments the list of files attached to the letter
     * @param date        date and time of sending or receiving letters
     * @return entity {@link edu.mail.core.entities.Letter}
     */
    Letter createLetter(Person idPerson, Folder idFolder, Account idAccount, boolean isSeen, String fromWhom, List<String> toWhom,
                        List<String> copy, String subject, String message, List<Attachment> attachments, Date date);

    /**
     * The method allows to create an entity {@link edu.mail.core.entities.Attachment}
     *
     * @param name      file name attached to the letter
     * @param files     binary representation of the file
     * @param idLetter  the message identifier which is attached to the file
     * @return entity {@link edu.mail.core.entities.Attachment}
     */
    Attachment createAttachment(String name, byte[] files, Letter idLetter, Folder idFolder, Account idAccount, Person idPerson);
}
