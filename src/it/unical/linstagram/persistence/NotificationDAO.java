package it.unical.linstagram.persistence;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import it.unical.linstagram.model.Notification;
import it.unical.linstagram.model.User;

@Repository
public class NotificationDAO implements INotificationDAO {

	@Override
	public List<Notification> getAllNotification(User user) {
		final Session session = HibernateUtil.getSession();
		final List<Notification> notifications = session
				.createQuery("FROM Notification n left outer join fetch n.post.media m WHERE n.userTo=:user order by n.date desc", Notification.class).setParameter("user", user)
				.list();
		session.close();
		return notifications;
	}

	@Override
	public Long getAllNotificationToSee(User user) {
		final Session session = HibernateUtil.getSession();
		final Long notifications = (Long) session.createQuery("SELECT COUNT(*) FROM Notification n WHERE n.userTo=:user and toSee=1")
				.setParameter("user", user).uniqueResult();
		session.close();
		return notifications;
	}
	
	public boolean isAlreadyFollower(User userTo,User userFrom) {
		final Session session = HibernateUtil.getSession();
		final BigInteger notifications = 
			 (BigInteger) session.createNativeQuery("Select count(*) FROM following as f "
			 		+ "WHERE f.followed=:userFrom and f.following=:userTo")
			 	.setParameter("userTo", userTo.getId()).setParameter("userFrom", userFrom.getId()).getSingleResult();
		session.close();
		return notifications !=BigInteger.ZERO;
	}
	
	public boolean isAlreadyFollowing(User userTo,User userFrom) {
		return isAlreadyFollower(userFrom,userFrom);
	}

}
