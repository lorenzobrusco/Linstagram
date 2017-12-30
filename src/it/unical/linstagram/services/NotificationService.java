package it.unical.linstagram.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.linstagram.dto.NotificationDTO;
import it.unical.linstagram.model.Notification;
import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.ModelDAO;
import it.unical.linstagram.persistence.NotificationDAO;

@Service
public class NotificationService {

	@Autowired
	private ModelDAO modelDAO;

	@Autowired
	private NotificationDAO notificationDAO;

	/**
	 * Used to save the notification
	 * 
	 * @param notification
	 */
	public void saveNotification(Notification notification) {
		modelDAO.save(notification);
	}

	/**
	 * Used to get all notification to see from db
	 * @param user
	 * @param maxNumberOfNotification 
	 * @return
	 */
	public List<NotificationDTO> getAllNotificationToSee(User user, int maxNumberOfNotification) {
		final List<Notification> notifications = notificationDAO.getAllNotificationToSee(user);
		final List<NotificationDTO> notificationDTO = new ArrayList<>();
		for (Notification notification : notifications) {
			notificationDTO.add(new NotificationDTO(notification));
			if (notificationDTO.size() > maxNumberOfNotification)
				break;
		}
		return notificationDTO;
	}
}
