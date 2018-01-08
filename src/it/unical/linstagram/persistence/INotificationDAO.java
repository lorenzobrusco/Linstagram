package it.unical.linstagram.persistence;

import java.util.List;

import it.unical.linstagram.model.Notification;
import it.unical.linstagram.model.User;

public interface INotificationDAO {
	
	List<Notification> getAllNotification(User user);
	int getAllNotificationToSee(User user);

}
