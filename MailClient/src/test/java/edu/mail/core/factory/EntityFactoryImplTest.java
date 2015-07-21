package edu.mail.core.factory;

import edu.mail.api.Gender;
import edu.mail.core.entities.*;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Test case for {@link edu.mail.core.factory.EntityFactoryImpl}
 *
 * @author Sofia Ruban
 */
public class EntityFactoryImplTest {

    private static final Logger log = Logger.getLogger(EntityFactoryImplTest.class);

    private static SimpleDateFormat sdfBD;

    private static Person person;
    private static Account account;
    private static Contact contact;
    private static Folder folder;
    private static Letter letter;
    private static Attachment attachment;

    @BeforeClass
    public static void init() throws ParseException {
        sdfBD = new SimpleDateFormat("dd.mm.yyyy");

        person = EntityFactoryImpl.getInstance().createPerson("sofia-ruban@yandex.ru", "58Cv12qw", "Sofia",
                "Ruban", Gender.WOMAN, sdfBD.parse("20.10.1994"), "Russia", "Moscow", "", null, null);
        account = EntityFactoryImpl.getInstance().createAccount("sofia-ruban@yandex.ru", "58Cv12qw",
                "smtp.yandex.ru", "imap.yandex.ru", null);
        contact = EntityFactoryImpl.getInstance().createContact("roma-dovgalyow@yandex.ru", "Roman", "Dovgalev");
        folder = EntityFactoryImpl.getInstance().createFolder(null, "Inbox", null, null, true, "Folder contains incoming user messages");
        letter = EntityFactoryImpl.getInstance().createLetter(null, null, null, false, "sofia-ruban@yandex.ru",
                new ArrayList<String>(), new ArrayList<String>(), "Test message", "Test message", new ArrayList<Attachment>(),
                null);
        attachment = EntityFactoryImpl.getInstance().createAttachment(null, null, null, null, null, null);
    }

    @Test
    public void testCreatePerson() throws Exception {
        log.trace("The call method testCreatePerson()");

        Map<String, Account> mailboxes = new HashMap<>();
        mailboxes.put(account.getId(), account);
        person.setMailboxes(mailboxes);

        Map<String, Contact> contacts = new HashMap<>();
        contacts.put(contact.getId(), contact);
        person.setContacts(contacts);

        assertNotNull("The identifier must not be equals null", person.getId());
        assertEquals("Field \"login\" does not match expected value", "sofia-ruban@yandex.ru", person.getLogin());
        assertEquals("Field \"password\" does not match expected value", "58Cv12qw", person.getPassword());
        assertEquals("Field \"name\" does not match expected value", "Sofia", person.getName());
        assertEquals("Field \"surname\" does not match expected value", "Ruban", person.getSurname());
        assertEquals("Field \"gender\" does not match expected value", Gender.WOMAN, person.getGender());
        assertEquals("Field \"birthday\" does not match expected value", "20.10.1994", sdfBD.format(person.getBirthday()));
        assertEquals("Field \"country\" does not match expected value", "Russia", person.getCountry());
        assertEquals("Field \"city\" does not match expected value", "Moscow", person.getCity());
        assertEquals("Field \"info\" does not match expected value", "", person.getInfo());
        assertEquals("The number of user accounts does not match expected", 1, person.getMailboxes().size());
        assertEquals("The number of contacts the user does not match the expected", 1, person.getContacts().size());
    }

    @Test
    public void testCreateAccount() throws Exception {
        log.trace("The call method testCreateAccount()");

        account.setIdPerson(person);

        assertNotNull("The identifier must not be equals null", account.getId());
        assertEquals("Field \"email\" does not match expected value", "sofia-ruban@yandex.ru", account.getEmail());
        assertEquals("Field \"password\" does not match expected value", "58Cv12qw", account.getPassword());
        assertEquals("Field \"outgoingMailServer\" does not match expected value", "smtp.yandex.ru", account.getOutgoingMailServer());
        assertEquals("Field \"incomingMailServer\" does not match expected value", "imap.yandex.ru", account.getIncomingMailServer());
        assertEquals("Field \"idPerson\" does not match expected value", person.getId(), account.getIdPerson().getId());
    }

    @Test
    public void testCreateContact() throws Exception {
        log.trace("The call method testCreateContact()");

        assertNotNull("The identifier must not be equals null", contact.getId());
        assertEquals("Field \"email\" does not match expected value", "roma-dovgalyow@yandex.ru", contact.getEmail());
        assertEquals("Field \"name\" does not match expected value", "Roman", contact.getName());
        assertEquals("Field \"surname\" does not match expected value", "Dovgalev", contact.getSurname());
    }

    @Test
    public void testCreateFolder() throws Exception {
        log.trace("The call method testCreateFolder()");

        folder.setIdAccount(account);
        folder.setIdPerson(person);

        assertNotNull("The identifier must not be equals null", folder.getId());
        assertEquals("Field \"idAccount\" does not match expected value", account.getId(), folder.getIdAccount().getId());
        assertEquals("Field \"name\" does not match expected value", "Inbox", folder.getName());
        assertNull("Field \"idParentFolder\" does not match expected value", folder.getIdParentFolder());
        assertEquals("Field \"idPerson\" does not match expected value", person.getId(), folder.getIdPerson().getId());
        assertTrue("Field \"isSystemFolder\" does not match expected value", folder.isSystemFolder());
        assertEquals("Field \"description\" does not match expected value", "Folder contains incoming user messages", folder.getDescription());
    }

    @Test
    public void testCreateLetter() throws Exception {
        log.trace("The call method testCreateLetter()");

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy kk:mm:ss");

        letter.setIdPerson(person);
        letter.setIdFolder(folder);
        letter.setIdAccount(account);
        List<String> recipients = new ArrayList<>();
        recipients.add(contact.getEmail());
        letter.setToWhom(recipients);
        List<Attachment> attachments = new ArrayList<>();
        attachments.add(attachment);
        letter.setAttachments(attachments);
        letter.setDate(new Date());

        assertNotNull("The identifier must not be equals null", letter.getId());
        assertEquals("Field \"idPerson\" does not match expected value", person.getId(), letter.getIdPerson().getId());
        assertEquals("Field \"idFolder\" does not match expected value", folder.getId(), letter.getIdFolder().getId());
        assertEquals("Field \"idAccount\" does not match expected value", account.getId(), letter.getIdAccount().getId());
        assertFalse("Field \"isSeen\" does not match expected value", letter.isSeen());
        assertEquals("Field \"fromWhom\" does not match expected value", "sofia-ruban@yandex.ru", letter.getFromWhom());
        assertEquals("Field \"toWhom\" does not match expected value", contact.getEmail(), letter.getToWhom());
        assertEquals("Field \"copy\" does not match expected value", "", letter.getCopy());
        assertEquals("Field \"subject\" does not match expected value", "Test message", letter.getSubject());
        assertEquals("Field \"message\" does not match expected value", "Test message", letter.getMessage());
        assertEquals("The number of attached files does not match the expected value", 1, letter.getAttachments().size());
        assertEquals("Field \"date\" does not match expected value", sdf.format(new Date()), sdf.format(letter.getDate()));
    }

    @Test
    public void testCreateAttachment() throws Exception {
        log.trace("The call method testCreateAttachment()");

        File file = new File(this.getClass().getResource("Test_file.jpg").toURI());
        System.out.println(file.getAbsolutePath());
        attachment.setName(file.getName());
        attachment.setFiles(Files.readAllBytes(file.toPath()));
        attachment.setIdLetter(letter);
        attachment.setIdFolder(folder);
        attachment.setIdAccount(account);
        attachment.setIdPerson(person);

        assertNotNull("The identifier must not be equals null", attachment.getId());
        assertEquals("Field \"name\" does not match expected value", "Test_file.jpg", attachment.getName());
        assertArrayEquals("Field \"files\" does not match expected value", Files.readAllBytes(file.toPath()), attachment.getFiles());
        assertEquals("Field \"idLetter\" does not match expected value", letter.getId(), attachment.getIdLetter().getId());
        assertEquals("Field \"idFolder\" does not match expected value", folder.getId(), attachment.getIdFolder().getId());
        assertEquals("Field \"idAccount\" does not match expected value", account.getId(), attachment.getIdAccount().getId());
        assertEquals("Field \"idPerson\" does not match expected value", person.getId(), attachment.getIdPerson().getId());
    }
}