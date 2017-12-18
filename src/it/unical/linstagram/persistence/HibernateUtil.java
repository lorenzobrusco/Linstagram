package it.unical.linstagram.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory factory;
	
	public static void CreateSessionFactory(boolean test) {
	
		if(factory == null) {
			if(test)
				factory =  new Configuration().configure("hibernateTest.cfg.xml").buildSessionFactory();
			else
				factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		}
	
	}
	
	public static Session getHibernateSession() {

		if(factory==null) {
			factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		}
		System.out.println(factory);
		final Session session = factory.openSession();
		return session;
	}
	
	public static Session getHibernateTestSession() {

		if(factory==null) {
			factory = new Configuration().configure("hibernateTest.cfg.xml").buildSessionFactory();
		}
		final Session session = factory.openSession();
		return session;
	}
}
