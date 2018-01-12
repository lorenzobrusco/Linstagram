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

	public static void initSessionFactory(boolean test) {

		if (factory == null) {
			Configuration configuration = new Configuration();
			if (test)
			{
				System.out.println( "ciao");
				factory = configuration.configure("hibernateTest.cfg.xml").buildSessionFactory();
			}
			else
			{
				String path = HibernateUtil.class.getClassLoader().getResource("").getPath();

				try {
					String fullPath = URLDecoder.decode(path, "UTF-8");
					fullPath = new File(fullPath).getPath();

					configuration.setProperty("hibernate.search.default.indexBase", fullPath + "/../build/indexes");
					//					configuration.setProperty("hihibernate.search.default.locking_strategy", "naive");

					factory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();

				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

	public static Session getSession() {

		//		if (factory == null) {
		//
		//			Configuration configuration = new Configuration();
		//			String path = HibernateUtil.class.getClassLoader().getResource("").getPath();
		//
		//			try {
		//				String fullPath = URLDecoder.decode(path, "UTF-8");
		//				fullPath = new File(fullPath).getPath();
		//
		//				configuration.setProperty("hibernate.search.default.indexBase", fullPath + "/../build/indexes");
		//				configuration.setProperty("hihibernate.search.default.locking_strategy", "naive");
		//
		//				if (!Files.exists(Paths.get(fullPath + "/../build/indexes"))) {
		//					factory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
		//					init();
		//				} else {
		//					factory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
		//				}
		//			} catch (UnsupportedEncodingException e) {
		//				// TODO Auto-generated catch block
		//				e.printStackTrace();
		//			}
		//
		//		}

		System.out.println(factory);
		final Session session = factory.openSession();
		return session;
	}
	

}