package it.unical.linstagram.services;

import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.ARG_OUT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.linstagram.dto.NotificationDTO;
import it.unical.linstagram.model.Notification;
import it.unical.linstagram.model.NotificationType;
import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.ModelDAO;
import it.unical.linstagram.persistence.NotificationDAO;
import it.unical.linstagram.persistence.PostDAO;
import it.unical.linstagram.persistence.UserDAO;

@Service
public class NotificationService {

	@Autowired
	private ModelDAO modelDAO;

	@Autowired
	private NotificationDAO notificationDAO;

	@Autowired
	private PostDAO postDAO;

	@Autowired
	private UserDAO userDAO;

	/**
	 * Used to save the notification
	 * 
	 * @param notification
	 */
	public void saveNotification(Notification notification) {
		modelDAO.save(notification);
	}

	/**
	 * Generate notification when user likes a post
	 * 
	 * @param user
	 * @param idPost
	 */
	public void generateLikeNotification(User user, int idPost) {
		final Post post = postDAO.getPostById(idPost);
		final Notification notification = new Notification(user, post.getUser(), post, null, NotificationType.LIKE);
		modelDAO.save(notification);
		System.out.println("notification");
	}

	/**
	 * Generate notification when user follow another user
	 * 
	 * @param user
	 * @param follow
	 */
	public void generateFollowNotification(User user, String follow) {
		final User userToFollow = userDAO.getUserByUsername(follow);
		final Notification notification = new Notification(user, userToFollow, null, null, NotificationType.FOLLOW);
		modelDAO.save(notification);
	}

	/**
	 * Used to get all notification from db
	 * 
	 * @param user
	 * @param maxNumberOfNotification
	 * @return
	 */
	public List<NotificationDTO> getAllNotification(User user, int maxNumberOfNotification) {
		final List<Notification> notifications = notificationDAO.getAllNotification(user);
		final List<NotificationDTO> notificationsDTO = new ArrayList<>();
		for (Notification notification : notifications) {
			boolean exist_request = userDAO.existRequestFollow(notification.getUserFrom().getUsername(),
					notification.getUserTo().getUsername());
			
			boolean alreadyFollow = notification.getUserTo().getFollowings().contains(notification.getUserFrom());
			if (exist_request)
				alreadyFollow = false;
			notificationsDTO.add(new NotificationDTO(notification, alreadyFollow));
			if (notification.isToSee()) {
				notification.setToSee(false);
				modelDAO.update(notification);
			}
			if (notificationsDTO.size() > maxNumberOfNotification)
				break;
		}
		return notificationsDTO;
	}

	public Long getAllNumberOfNotificationToSee(User user) {
		return notificationDAO.getAllNotificationToSee(user);
	}
}
