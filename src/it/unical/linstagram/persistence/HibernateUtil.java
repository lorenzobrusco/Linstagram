package it.unical.linstagram.persistence;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.MassIndexer;
import org.hibernate.search.Search;
import org.hibernate.search.batchindexing.MassIndexerProgressMonitor;
import org.hibernate.search.batchindexing.impl.SimpleIndexingProgressMonitor;

import it.unical.linstagram.model.Hashtag;
import it.unical.linstagram.model.User;

public class HibernateUtil {

	private static SessionFactory factory;
	private static SessionFactory testFactory;

	public static void CreateSessionFactory(boolean test) {


		if (test && testFactory == null)
			testFactory = new Configuration().configure("hibernateTest.cfg.xml").buildSessionFactory();
		else if (!test && factory == null)
			factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		//		init();

	}

	public static Session getHibernateSession() {


		if (factory == null) {
			Configuration configuration = new Configuration();
			factory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();

			// TODO Se vuoi le stampe caccia il commento
			System.out.println(factory);
		}
		final Session session = factory.openSession();
		return session;
	}

	public static Session getHibernateTestSession() {

		if (testFactory == null) {

			Configuration configuration = new Configuration();
			factory = configuration.configure("hibernateTest.cfg.xml").buildSessionFactory();
			//			init();
		}
		final Session session = testFactory.openSession();
		return session;
	}

	public static void init() {
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

		MassIndexerProgressMonitor monitor = new SimpleIndexingProgressMonitor();
		try {
			massIndexer.batchSizeToLoadObjects(25)
			.cacheMode(CacheMode.NORMAL)
			.threadsToLoadObjects(12)
			.idFetchSize(150)
			.transactionTimeout(1800)
			.progressMonitor(monitor) // a MassIndexerProgressMonitor implementation
			.startAndWait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.err.println("mass reindexing interrupted: " + e.getMessage());
		} finally {
			txtSession.flushToIndexes();
		}
	}

}