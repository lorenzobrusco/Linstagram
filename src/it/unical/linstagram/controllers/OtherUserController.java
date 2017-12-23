package it.unical.linstagram.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.unical.linstagram.dto.UserPrivateDTO;
import it.unical.linstagram.helper.UserManager;
import it.unical.linstagram.model.User;
import it.unical.linstagram.services.UserService;

@Controller
public class OtherUserController {

	@Autowired
	private UserService userService;
	
	
	@RequestMapping("/usersList")
	public String usersListPage(HttpSession session, Model model) {
		if(UserManager.checkLogged(session)) {
			List<User> users = userService.getUsersList();
			model.addAttribute("users", users);
			return "usersListPage";
		}
		return "redirect:/";
	}
	
	@RequestMapping("userPage")
	public String getUserPage(HttpSession session, Model model, @RequestParam("usernameOther") String usernameOther) {
		
		User user = (User) session.getAttribute("user");
		UserPrivateDTO userDTO = userService.getOtherUser(user, usernameOther);
		model.addAttribute("user", userDTO);
		model.addAttribute("userSession", user);
		return "otherUserPage";
	}
	
	
}
