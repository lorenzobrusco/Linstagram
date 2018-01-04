package it.unical.linstagram.controllers;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import it.unical.linstagram.dto.NotificationDTO;
import it.unical.linstagram.model.User;
import it.unical.linstagram.services.NotificationService;

@Controller
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	private int maxNumberOfNotification = 50;

	@RequestMapping("openNotification")
	@ResponseBody
	public Collection<NotificationDTO> openNotification(HttpSession session) {
		Collection<NotificationDTO> notifications = notificationService
				.getAllNotificationToSee((User) session.getAttribute("user"), maxNumberOfNotification);
		System.out.println("[SIZE NOTIFICATION]" + notifications.size());
		return notifications;
	}
}
