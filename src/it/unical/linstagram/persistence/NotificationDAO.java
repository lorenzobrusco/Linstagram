package it.unical.linstagram.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import it.unical.linstagram.model.Notification;
import it.unical.linstagram.model.User;

@Repository
public class NotificationDAO implements INotificationDAO{

	@Override
	public List<Notification> getAllNotification(User user) {
		return null;
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
