package com.mkyong;

import java.util.Date;
import java.util.Locale;

import com.company.core.entity.Contact;
import org.hibernate.Session;
import com.mkyong.util.HibernateUtil;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
	public static void main(String[] args) {
		System.out.println("Maven + Hibernate Annotation + Oracle");
		Locale.setDefault(Locale.ENGLISH);

		Contact contact = new Contact();
		contact.setEmail("r-sophiya@yanex.ru");
		contact.setName("Sophiya");
		contact.setSurname("Ruban");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mailDB");



//		Session session = HibernateUtil.getSessionFactory().openSession();
//
//		session.beginTransaction();
//		DBUser user = new DBUser();
//
//		session.save(contact);
//		session.save(user);
//		session.getTransaction().commit();
	}
}
