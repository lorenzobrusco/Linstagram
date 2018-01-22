package it.unical.linstagram.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import it.unical.linstagram.dto.NotificationDTO;
import it.unical.linstagram.helper.MessageNotification;
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

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	/**
	 * Used to save the notification
	 * 
	 * @param notification
	 */
	public void saveNotification(Notification notification) {
		if(notification.getUserFrom().getId() == notification.getUserTo().getId())
			return;
		Notification oldNotification = notificationDAO.existsNotification(notification);
		if (oldNotification != null) {
			modelDAO.delete(Notification.class, oldNotification.getId());
			modelDAO.save(notification);
		} else {
			modelDAO.save(notification);
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteNotification(int id) {
		return modelDAO.delete(Notification.class, id);
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
		this.saveNotification(notification);
	}

	/**
	 * Generate comment when user comments a post
	 * 
	 * @param user
	 * @param idPost
	 */
	public void generateCommentNotification(User user, int idPost) {
		final Post post = postDAO.getPostById(idPost);
		if (post.getUser().getId() != user.getId()) {
			final Notification notification = new Notification(user, post.getUser(), post, null,
					NotificationType.COMMENT);
			this.saveNotification(notification);
		}
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
		this.saveNotification(notification);
	}
	
	public void generateTagNotifications(User user, Set<User> usersTagged, Post post) {
		for(User usertagged: usersTagged) {
			if(user.getId() != usertagged.getId())
			modelDAO.save(new Notification(user, usertagged, post, null, NotificationType.TAG));
		}
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
			boolean existRequestTo = userDAO.existRequestFollow(notification.getUserFrom().getUsername(),
					notification.getUserTo().getUsername());
			boolean existRequestFrom = userDAO.existRequestFollow(notification.getUserTo().getUsername(),
					notification.getUserFrom().getUsername());
			boolean alreadyFollowing = userDAO.isAlreadyFollowing(notification.getUserTo(), notification.getUserFrom());
			boolean alreadyFollowed = userDAO.isAlreadyFollower(notification.getUserTo(), notification.getUserFrom());
			notificationsDTO.add(new NotificationDTO(notification, alreadyFollowing, alreadyFollowed, existRequestTo,
					existRequestFrom));
			if (notification.isToSee()) {
				notification.setToSee(false);
			}
			if (notificationsDTO.size() > maxNumberOfNotification)
				break;
		}
		modelDAO.updateList(notifications);
		return notificationsDTO;
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	public Long getAllNumberOfNotificationToSee(User user) {
		return notificationDAO.getAllNotificationToSee(user);
	}

	/**
	 * Send notification to users subscribed on channel "/user/queue/notify". The
	 * message will be sent only to the user with the given username.
	 * 
	 * @param notification
	 * @param username
	 * 
	 */
	public void notify(MessageNotification notification, String username) {
		messagingTemplate.convertAndSendToUser(username, "/queue/notify", notification.getMessage());
		return;
	}
}
