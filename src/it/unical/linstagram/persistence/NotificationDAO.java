package it.unical.linstagram.persistence;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import it.unical.linstagram.model.Notification;
import it.unical.linstagram.model.User;

@Repository
public class NotificationDAO implements INotificationDAO {

	@Override
	public List<Notification> getAllNotification(User user) {
		final Session session = HibernateUtil.getHibernateSession();
		final List<Notification> notifications = session
				.createNativeQuery("SELECT * FROM notification ", Notification.class)
				.list();
		session.close();
		return notifications;
	}

	@Override
	public List<Notification> getAllNotificationToSee(User user) {
		return null;
	}

	@Override
	public List<Notification> getAllNotificationAlreadySee(User user) {
		return null;
	}

}
