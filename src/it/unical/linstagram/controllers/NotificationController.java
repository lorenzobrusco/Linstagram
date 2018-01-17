package it.unical.linstagram.controllers;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import it.unical.linstagram.dto.NotificationDTO;
import it.unical.linstagram.helper.MessageNotification;
import it.unical.linstagram.helper.MessageResponse;
import it.unical.linstagram.model.User;
import it.unical.linstagram.services.MessageCode;
import it.unical.linstagram.services.NotificationService;

@Controller
public class NotificationController {
	
	@Autowired
	private NotificationService notificationService;

	private int maxNumberOfNotification = 50;

	@RequestMapping(value = "sendNotification", method = RequestMethod.POST)
	@ResponseBody
	public void someAction(@RequestParam String user) {
		notificationService.notify(new MessageNotification("update"), user);
	}
	
	@RequestMapping(value = "openNotification", method = RequestMethod.POST)
	@ResponseBody
	public Collection<NotificationDTO> openNotification(HttpSession session) {
		Collection<NotificationDTO> notifications = notificationService
				.getAllNotification((User) session.getAttribute("user"), maxNumberOfNotification);
		return notifications;
	}

	@RequestMapping(value = "notificationToSee", method = RequestMethod.POST)
	@ResponseBody
	public Long notificationNumber(HttpSession session) {
		return notificationService.getAllNumberOfNotificationToSee((User) session.getAttribute("user"));
	}
	
	@RequestMapping(value = "deleteNotification")
	@ResponseBody
	public String deleteNotification(HttpSession session, @RequestParam int id) {
		User user = (User) session.getAttribute("user");
		if (notificationService.deleteNotification(id)) {
			return new MessageResponse(MessageCode.OK, user, "OK").getMessage();
		}
		return new MessageResponse(MessageCode.FAILED, user, "Failed").getMessage();
	}
}
