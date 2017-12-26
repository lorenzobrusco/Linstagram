package it.unical.linstagram.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.MassIndexer;
import org.hibernate.search.Search;

import it.unical.linstagram.model.Hashtag;

public class HibernateUtil {

	private static SessionFactory factory;

	public static void CreateSessionFactory(boolean test) {

		if(factory == null) {
			if(test)
				factory =  new Configuration().configure("hibernateTest.cfg.xml").buildSessionFactory();
			else
				factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		}

		init();

	}

	public static Session getHibernateSession() {

		if(factory==null) {
			factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

			init();

		}
		System.out.println(factory);
		final Session session = factory.openSession();
		return session;
	}

	public static Session getHibernateTestSession() {

		if(factory==null) {
			factory = new Configuration().configure("hibernateTest.cfg.xml").buildSessionFactory();

			init();
		}
		final Session session = factory.openSession();
		return session;
	}

	public static void init()
	{
		Session session = factory.openSession();
		reindex(Hashtag.class, session);
		session.close();
	}

	/**
	 * Regenerates the index for a given class
	 * 
	 * @param clazz
	 *            the class
	 * @param sess
	 *            the hibernate session
	 */
	public static void reindex(Class clazz, Session sess) {
		FullTextSession txtSession = Search.getFullTextSession(sess);
		MassIndexer massIndexer = txtSession.createIndexer(clazz);
		try {
			massIndexer.startAndWait();
		} catch (InterruptedException e) {
			System.err.println("mass reindexing interrupted: " + e.getMessage());
		} finally {
			txtSession.flushToIndexes();
		}
	}

}
