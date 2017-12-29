package it.unical.linstagram.model.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;
import it.unical.linstagram.model.Notification;
import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.HibernateUtil;

public class TestNotification {
	
	@SuppressWarnings("unchecked")
	@Test
	public void testNotification() {
		User user = new User("lorenzo", "lorenzo@hotmail.it", "peppepeppe"); 
		User user1 = new User("ciccio2", "ciccio2@hotmail.it", "peppepeppe");
		
		Notification notification = new Notification(user1,user,"follow", true);
		Notification notification1 = new Notification(user,user1,"follow", true);
		
		final Session session = HibernateUtil.getHibernateSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(notification);
			session.save(notification1);
			transaction.commit();

		} catch (Exception e) {
			transaction.rollback();
		}
		
		
		List<Notification> notifications = session.createNativeQuery("SELECT * FROM notification").list();
	
	
		Assert.assertEquals(2,notifications.size());
		session.close();

	}
}
