package it.unical.linstagram.model.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.BeforeClass;

import it.unical.linstagram.persistence.HibernateUtil;

public class AbstractModelTest {
	
	@After
	public void cleanDB()
	{	
		final Session session = HibernateUtil.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.createNativeQuery("TRUNCATE SCHEMA PUBLIC AND COMMIT").executeUpdate();
			transaction.commit();
			
		} catch (Exception e) {
			transaction.rollback();
			
		} finally {
			session.close();
		}
	}
	
	@BeforeClass
	public static void setSessionFactory ()
	{
		HibernateUtil.initSessionFactory(true);
	}
}
