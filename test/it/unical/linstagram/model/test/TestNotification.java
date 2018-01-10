package it.unical.linstagram.model.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;
import it.unical.linstagram.model.Notification;
import it.unical.linstagram.model.NotificationType;
import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.HibernateUtil;

public class TestNotification extends AbstractModelTest {

	@SuppressWarnings("unchecked")
	@Test
	public void testNotification() {
		User user = new User("lorenzo2", "lorenzo2@hotmail.it", "peppepeppe");
		User user1 = new User("ciccio2", "ciccio2@hotmail.it", "peppepeppe");

		Notification notification = new Notification(user1, user, null, null, NotificationType.FOLLOW);
		Notification notification1 = new Notification(user, user1, null, null, NotificationType.FOLLOW);

		final Session session = HibernateUtil.getSession();
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
		Assert.assertEquals(2, notifications.size());
		session.close();

	}
}
