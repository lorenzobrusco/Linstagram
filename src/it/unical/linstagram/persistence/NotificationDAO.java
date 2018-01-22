package it.unical.linstagram.persistence;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import it.unical.linstagram.model.Notification;
import it.unical.linstagram.model.NotificationType;
import it.unical.linstagram.model.User;

@Repository
@SuppressWarnings("unchecked")
public class NotificationDAO implements INotificationDAO {

	@Override
	public List<Notification> getAllNotification(User user) {
		final Session session = HibernateUtil.getSession();
		final List<Notification> notifications = session.createQuery(
				"FROM Notification n left outer join fetch n.post.media m WHERE n.userTo=:user order by n.date desc",
				Notification.class).setParameter("user", user).list();
		session.close();
		return notifications;
	}

	@Override
	public Long getAllNotificationToSee(User user) {
		final Session session = HibernateUtil.getSession();
		final Long notifications = (Long) session
				.createQuery("SELECT COUNT(*) FROM Notification n WHERE n.userTo=:user and toSee=1")
				.setParameter("user", user).uniqueResult();
		session.close();
		return notifications;
	}

	public Notification existsNotification(Notification notification) {
		final Session session = HibernateUtil.getSession();
		List<Notification> notifications = null;
		if (notification.getType() == NotificationType.LIKE) {
			notifications = session
					.createQuery("FROM Notification n WHERE n.userTo=:userTo and n.userFrom=:userFrom "
							+ "and n.post=:post and n.type=2")
					.setParameter("userTo", notification.getUserTo())
					.setParameter("userFrom", notification.getUserFrom()).setParameter("post", notification.getPost())
					.list();
		} else {
			notifications = session
					.createQuery(
							"FROM Notification n WHERE n.userTo=:userTo and n.userFrom=:userFrom " + "and n.type=0")
					.setParameter("userTo", notification.getUserTo())
					.setParameter("userFrom", notification.getUserFrom()).list();
		}
		Notification ntf = null;
		if (notifications.size() > 0)
			ntf = notifications.get(0);
		session.close();
		return ntf;
	}

	public int existsFollowRequest(User userFrom, User userTo) {
		final Session session = HibernateUtil.getSession();

		List<Notification> notifications = session
				.createQuery("FROM Notification n WHERE n.userTo=:userTo and n.userFrom=:userFrom and n.type=0")
				.setParameter("userTo", userTo).setParameter("userFrom", userFrom).list();
		if (notifications.size() > 0)
			return notifications.get(0).getId();
		session.close();
		return -1;
	}

}
