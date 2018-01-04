package it.unical.linstagram.persistence;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.MassIndexer;
import org.hibernate.search.Search;

import it.unical.linstagram.model.Hashtag;
import it.unical.linstagram.model.User;

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

			Configuration configuration = new Configuration();
			String path = HibernateUtil.class.getClassLoader().getResource("").getPath();

			try {
				String fullPath = URLDecoder.decode(path, "UTF-8");
				configuration.setProperty("hibernate.search.default.indexBase",fullPath+"../build/indexes");
				configuration.setProperty("hihibernate.search.default.locking_strategy", "naive");

				if (!Files.exists(Paths.get(fullPath+"../build/indexes"))) {
					factory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
					//init();
				}
				else
				{
					factory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();					
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
		System.out.println(factory);
		final Session session = factory.openSession();
		return session;
	}

	public static Session getHibernateTestSession() {

		if(factory==null) {

			Configuration configuration = new Configuration();
			factory = configuration.configure("hibernateTest.cfg.xml").buildSessionFactory();
			init();
		}
		final Session session = factory.openSession();
		return session;
	}

	public static void init()
	{
		Session session = factory.openSession();
		System.out.println("SONO LA INIT");
		reindex(Hashtag.class, session);
		reindex(User.class, session);
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