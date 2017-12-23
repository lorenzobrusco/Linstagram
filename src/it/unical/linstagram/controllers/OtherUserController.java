package it.unical.linstagram.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.unical.linstagram.dto.UserDTO;
import it.unical.linstagram.dto.UserPrivateDTO;
import it.unical.linstagram.dto.UserPublicDTO;
import it.unical.linstagram.helper.MessageResponce;
import it.unical.linstagram.helper.UserManager;
import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;
import it.unical.linstagram.services.MessageCode;
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
		UserDTO userDTO = userService.getOtherUser(user, usernameOther);
		
		model.addAttribute("user", userDTO);
		model.addAttribute("userSession", user);
		return "otherUserProfile";
	}
	
	@RequestMapping("followUser")
	@ResponseBody
	public String followUser(HttpSession session, Model model, @RequestParam("username") String usernameOther) {
		
		User user = (User) session.getAttribute("user");
		User userToFollow = userService.getUser(usernameOther);
		
		if (!userService.addFollowing(user, userToFollow))
			return new MessageResponce(MessageCode.USERNAME_FAILED, user, "Non è stato possibile cambiare la data di nascita.").getMessage();
		
		return new MessageResponce(MessageCode.OK, user, "OK").getMessage();
	}
	
	@RequestMapping("unfollowUser")
	@ResponseBody
	public String unfollowUser(HttpSession session, Model model, @RequestParam("username") String usernameOther) {
		
		User user = (User) session.getAttribute("user");
		User userToFollow = userService.getUser(usernameOther);
		
		if (!userService.removeFollowing(user, userToFollow))
			return new MessageResponce(MessageCode.USERNAME_FAILED, user, "Non è stato possibile cambiare la data di nascita.").getMessage();
		
		return new MessageResponce(MessageCode.OK, user, "OK").getMessage();
	}
	
}
