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
				.createQuery("FROM Notification n WHERE n.userTo=:user", Notification.class)
				.setParameter("user", user)
				.list();
		session.close();
		return notifications;
	}

	@Override
	public int getAllNotificationToSee(User user) {
		return 0;
	}


}
