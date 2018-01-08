package it.unical.linstagram.persistence;

import java.util.List;

import it.unical.linstagram.model.Notification;
import it.unical.linstagram.model.User;

public interface INotificationDAO {
	
	List<Notification> getAllNotification(User user);
	List<Notification> getAllNotificationToSee(User user);
	List<Notification> getAllNotificationAlreadySee(User user);

}
