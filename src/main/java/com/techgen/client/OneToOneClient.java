package com.techgen.client;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.techgen.entity.Passport;
import com.techgen.entity.Person;
import com.techgen.util.HibernateUtil;

public class OneToOneClient {

	public static void main(String[] args) {
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = HibernateUtil.getSessionFactory();

			session = sessionFactory.openSession();

			Transaction transaction = session.getTransaction();

			transaction.begin();

			// persistPersonPassport(session);
			// deletePerson(session);
			// deletePassport(session);
			// persistPerson(session);
			// persistPassport(session);
			// updatePersonPassport(session);
			// updatePassportPerson(session);

			transaction.commit();

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
			if (sessionFactory != null) {
				sessionFactory.close();
			}
		}
	}

	private static Person getPerson(Session session, Long id) {
		Person person = session.get(Person.class, id);
		return person;
	}

	private static Passport getPassport(Session session, Long id) {
		Passport passport = session.get(Passport.class, id);
		return passport;
	}

	private static void persistPersonPassport(Session session) {
		Passport passport = createPassport();
		Person person = createPerson(passport);
		session.persist(person);
	}

	private static void deletePerson(Session session) {
		Person person = getPerson(session, 1l);
		session.remove(person);
	}

	private static void deletePassport(Session session) {
		Passport passport = getPassport(session, 1l);
		session.remove(passport);
	}

	private static void persistPassport(Session session) {
		Passport passport = createPassport();
		session.persist(passport);
	}

	private static void persistPerson(Session session) {
		Person person = createPerson(null);
		session.persist(person);
	}

	private static void updatePersonPassport(Session session) {
		Person person = getPerson(session, 3l);
		Passport passport = getPassport(session, 2l);
		person.setPassport(passport);
	}

	private static void updatePassportPerson(Session session) {
		Passport passport = getPassport(session, 3l);
		Person person = getPerson(session, 3l);
		passport.setCity("Indore");
		person.setPassport(passport);
	}

	private static Passport createPassport() {
		Passport passport = new Passport();
		passport.setNumber("DFDFE-45454");
		passport.setCity("Bhopal");
		passport.setIssuedDate(new Date());
		return passport;
	}

	private static Person createPerson(Passport passport) {
		Person person = new Person();
		person.setGender("Male");
		person.setAge(23);
		person.setName("Vivek");
		person.setPassport(passport);
		return person;
	}

}
