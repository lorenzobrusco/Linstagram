package it.unical.linstagram.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.linstagram.model.Notification;
import it.unical.linstagram.persistence.ModelDAO;

@Service
public class NotificationService {

	@Autowired
	private ModelDAO modelDao;	
	

	/**
	 * Used to save the notification
	 * @param notification
	 */
	public void saveNotification(Notification notification) {
		modelDao.save(notification);
	}
	
}
