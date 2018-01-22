package it.unical.linstagram.persistence;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory factory;
	private static String configPath = null;

	public static void initConfigPath ()
	{
		String path = HibernateUtil.class.getClassLoader().getResource("").getPath();

		try {
			configPath = URLDecoder.decode(path, "UTF-8");
			configPath = new File(configPath).getPath();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}


	}

	public static void initSessionFactory(boolean test) {


		if (configPath == null)
		{
			initConfigPath();
		}
		if (factory == null) {
			Configuration configuration = new Configuration();
			if (test)
			{
				System.out.println( "ciao");
				factory = configuration.configure("hibernateTest.cfg.xml").buildSessionFactory();
			}
			else
			{
				//strategy
				configuration.setProperty("hibernate.search.default.locking_strategy", "none");
				//hibernate.search.default.indexBase
				configuration.setProperty("hibernate.search.default.indexBase", configPath + "/../build/indexes");
				factory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();


			}
		}

	}

	public static Session getSession() {
		final Session session = factory.openSession();
		return session;
	}

	public static String getConfigPath() {
		return configPath;
	}


}