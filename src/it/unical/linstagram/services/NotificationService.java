package it.unical.linstagram.services;

import java.util.ArrayList;
import java.util.List;

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
	 * Used to get all notification to see from db
	 * 
	 * @param user
	 * @param maxNumberOfNotification
	 * @return
	 */
	public List<NotificationDTO> getAllNotificationToSee(User user, int maxNumberOfNotification) {
		final List<Notification> notifications = notificationDAO.getAllNotification(user);
		System.out.println(notifications.size());
		final List<NotificationDTO> notificationDTO = new ArrayList<>();
		for (Notification notification : notifications) {
			notificationDTO.add(new NotificationDTO(notification));
			if (notificationDTO.size() > maxNumberOfNotification)
				break;
		}
		return notificationDTO;
	}
}
