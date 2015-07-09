package com.company.core.dao;

import com.company.api.Gender;
import com.company.core.entity.*;
import com.company.core.factory.EntityFactoryImpl;
import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

/**
 * @author Sophie
 * @date 27.06.2015.
 */
public class JPADAOTest {

    private static JPADAO<Account> accountJPADAO = null;
    private static JPADAO<Attachment> attachmentJPADAO = null;
    private static JPADAO<Contact> contactJPADAO = null;
    private static JPADAO<Folder> folderJPADAO = null;
    private static JPADAO<Letter> letterJPADAO = null;
    private static JPADAO<Person>  personJPADAO = null;

    private static EntityFactoryImpl factory = (EntityFactoryImpl) EntityFactoryImpl.getInstance();

    private  static Account account = null;
    private static Attachment  attachment = null;
    private static Contact contact = null;
    private static Folder folder =  null;
    private static Letter letter = null;
    private static Person person = new Person("duma@mail.ru", "password","Sanya","Saharov",
            Gender.MAN, new Date(1),"Russia", "Moscow","", new HashMap<String,Account>(), new HashMap<String, Contact>());


    @BeforeClass
    public static void setUp() throws Exception {
        Locale.setDefault(Locale.ENGLISH);
        accountJPADAO = new JPADAO();
        attachmentJPADAO = new JPADAO();
        contactJPADAO = new JPADAO();
        folderJPADAO = new JPADAO();
        letterJPADAO = new JPADAO();
        personJPADAO = new JPADAO();

        contact = factory.createContact("cool@dotnet.com", "Dwayne", "Johnson");
        account = factory.createAccount("example@yandex.ru", "12345", "yandex", "rambler", person);
        folder = factory.createFolder(account, "newFolder3", null, person, true, "root folder");

        Map<String, Contact> contacts = new HashMap<>();
        contacts.put(contact.getId(), contact);
        person.setContacts(contacts);

        ArrayList<String> toWhom = new ArrayList<>();
        toWhom.add("mymail@yandex.ru");
        toWhom.add("mymail@ramber.ru");
        ArrayList<String> copy = new ArrayList<>();
        copy.add("mymail1@yandex.ru");
        copy.add("mymail1@ramber.ru");

        letter = factory.createLetter(person, folder, account, true, "newaddresseed@gmail.com", toWhom, copy, "news",
                "Call me as soon as you can.", null, new Date(1455555555));
        attachment = factory.createAttachment("picture", new byte[2], letter, folder, account, person);
        ArrayList<Attachment> attachments = new ArrayList<>();
        attachments.add(attachment);

        letter.setAttachments(attachments);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        accountJPADAO.stopEntityManager();
        attachmentJPADAO.stopEntityManager();
        contactJPADAO.stopEntityManager();
        folderJPADAO.stopEntityManager();
        letterJPADAO.stopEntityManager();
        personJPADAO.stopEntityManager();
    }

    @Ignore
    @Test
    public void testAddEntity() throws Exception {
        System.err.println("testAddEntity");
        personJPADAO.addEntity(person);
        assertTrue("Contains entity", personJPADAO.isContains(person));

        accountJPADAO.addEntity(account);
        assertTrue("Contains entity", accountJPADAO.isContains(account));
        assertTrue("Contains entity", contactJPADAO.isContains(contact));
        folderJPADAO.addEntity(folder);
        assertTrue("Contains entity", folderJPADAO.isContains(folder));
        letterJPADAO.addEntity(letter);
        assertTrue("Contains entity", letterJPADAO.isContains(letter));
        assertTrue("Contains entity", attachmentJPADAO.isContains(attachment));

        personJPADAO.removeEntity(person);

    }

    @Ignore
    @Test
    public void testGetAllEntity() throws Exception {
        System.err.println("testGetAllEntity");

    }

//    @Ignore
    @Test
    public void testFindEntityById() throws Exception {
        System.err.println("testFindEntityById");

        personJPADAO.addEntity(person);
        accountJPADAO.addEntity(account);
        folderJPADAO.addEntity(folder);
        letterJPADAO.addEntity(letter);

        assertNotNull("Exists entity in DB", accountJPADAO.findEntityById(account.getId()));
        assertNotNull("Exists entity in DB", attachmentJPADAO.findEntityById(attachment.getId()));
        assertNotNull("Exists entity in DB", contactJPADAO.findEntityById(contact.getId()));
        assertNotNull("Exists entity in DB", folderJPADAO.findEntityById(folder.getId()));
        assertNotNull("Exists entity in DB", letterJPADAO.findEntityById(letter.getId()));
        assertNotNull("Exists entity in DB", personJPADAO.findEntityById(person.getId()));

        personJPADAO.removeEntity(person);

        letter.setAttachments(new ArrayList<Attachment>());
        letterJPADAO.updateEntity(letter);

        assertNull("Exists entity in DB", attachmentJPADAO.findEntityById(attachment.getId()));
//        contactJPADAO.removeEntity(contact);

        assertNull("Exists entity in DB", personJPADAO.findEntityById(person.getId()));
        assertNull("Exists entity in DB", contactJPADAO.findEntityById(contact.getId()));

        assertNull("Exists entity in DB", accountJPADAO.findEntityById(account.getId()));
        assertNull("Exists entity in DB", folderJPADAO.findEntityById(folder.getId()));
        assertNull("Exists entity in DB", letterJPADAO.findEntityById(letter.getId()));

    }

    @Ignore
    @Test
    public void testUpdateEntity() throws Exception {
        System.err.println("testUpdateEntity");
    }

    @Ignore
    @Test
    public void testRemoveEntity() throws Exception {
        System.err.println("testRemoveEntity");
        personJPADAO.addEntity(person);
        accountJPADAO.addEntity(account);
        folderJPADAO.addEntity(folder);
        letterJPADAO.addEntity(letter);

        folderJPADAO.removeEntity(folder);
//        letterJPADAO.removeEntity(letter);
        assertFalse("Doesn't contain entity", folderJPADAO.isContains(folder));
        assertFalse("Doesn't contain entity", letterJPADAO.isContains(letter));
        assertFalse("Doesn't contain entity", attachmentJPADAO.isContains(attachment));
//
//        personJPADAO.removeEntity(person);
////        letterJPADAO.removeEntity(letter);
//        assertFalse("Doesn't contain entity", personJPADAO.isContains(person));
//        assertFalse("Doesn't contain entity", contactJPADAO.isContains(contact));
//        assertFalse("Doesn't contain entity", accountJPADAO.isContains(account));

    }
}