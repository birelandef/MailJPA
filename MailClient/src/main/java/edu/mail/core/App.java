package edu.mail.core;

import java.util.*;

import edu.mail.api.Gender;
import edu.mail.core.dao.JPADAO;
import edu.mail.core.entities.*;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class App {
	public static void main(String[] args) {
		System.out.println("Maven + Hibernate Annotation + Oracle");
		Locale.setDefault(Locale.ENGLISH);

		Contact contact = new Contact();
		contact.setEmail("r-sophiya@yanex.ru");
		contact.setName("Sophiya");
		contact.setSurname("Ruban");

		Map<String, Contact> contacts = new HashMap<>();
		contacts.put(contact.getId(), contact);

		Person person = new Person("login", "password", "Vitya", "Savrasov", Gender.MAN, new Date(1),
				"Russia", "Moscow", "", new HashMap<String, Account>(),contacts);

		Account account = new Account("myemail@yandex.ru","qwerty","yandex", "mail", person);
		Map<String, Account> accounts = new HashMap<>();
		accounts.put(account.getId(), account);

		person.setMailboxes(accounts);

		Folder folder = new Folder(account, "New Folder", null, person, true, "my first insert folder");

		ArrayList<String> toWhom = new ArrayList<>();
		toWhom.add("firstaddressees@yandex.ru");
		toWhom.add("secondaddressees@yandex.ru");
		ArrayList<String> copy = new ArrayList<>();
		copy.add("firstaddressees@yandex.ru");
		copy.add("secondaddressees@yandex.ru");

		Letter letter = new Letter(person, folder, account, true, "newaddresseed@gmail.com",toWhom, copy, "news",
				"Call me as soon as you can.", null, new Date(1455555555));

		Attachment attachment = new Attachment("foto", new byte[2],letter, folder, account, person);

		ArrayList<Attachment> attachments = new ArrayList<>();
		attachments.add(attachment);
		letter.setAttachments(attachments);

//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mailDB");
//		EntityManager em = emf.createEntityManager();
//
//		EntityTransaction tx = em.getTransaction();
//
//
//		tx.begin();
//		em.persist(contact);
//		em.persist(person);
//		em.persist(account);
//		em.persist(folder);
//		em.persist(letter);
//		em.persist(attachment);
//		tx.commit();
//
//		tx.begin();
//		em.remove(folder);
//		tx.commit();
//
//
//		em.close();
//		emf.close();

		JPADAO<Person> jpadao = new JPADAO("mailDB");
		jpadao.addEntity(person);
		person.setCity("New Mex");
		jpadao.updateEntity(person);
		Entity entityById = jpadao.findEntityById(person.getId());
		System.out.println(entityById.getId());
		Collection<Person> allEntity = jpadao.getAllEntity(Person.class);
		Iterator<Person> iterator  = allEntity.iterator();
		while (iterator.hasNext()){
			System.out.println(iterator.next().toString());
		}
		jpadao.stopEntityManager();
	}
}
