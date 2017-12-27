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
import it.unical.linstagram.helper.MessageResponse;
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
		if (usernameOther.equals(user.getUsername())) 
			return "redirect:/profile";
		
		UserDTO userDTO = userService.getOtherUser(user, usernameOther);
		model.addAttribute("followers", userService.getFollowers(userDTO.getUsername()));
		model.addAttribute("followings", userService.getFollowings(userDTO.getUsername()));
		model.addAttribute("followingsUserSession", userService.getFollowings(user.getUsername()));
		
		model.addAttribute("user", userDTO);
		model.addAttribute("userSession", user);
		return "otherUserProfile";
	}
	
	@RequestMapping("followUser")
	@ResponseBody
	public String followUser(HttpSession session, Model model, @RequestParam("username") String usernameToFollow) {
		User user = (User) session.getAttribute("user");
		
		if (!userService.addFollowing(user.getUsername(), usernameToFollow, user))
			return new MessageResponse(MessageCode.FOLLOW_FAILED, user, "Non è stato possibile inserire l'utente come following.").getMessage();
		
		return new MessageResponse(MessageCode.OK, user, "OK").getMessage();
	}
	
	@RequestMapping("unfollowUser")
	@ResponseBody
	public String unfollowUser(HttpSession session, Model model, @RequestParam("username") String usernameToFollow) {

		User user = (User) session.getAttribute("user");
		System.out.println(user.getUsername()+" "+usernameToFollow);
		if (!userService.removeFollowing(user.getUsername(), usernameToFollow, user))
			return new MessageResponse(MessageCode.UNFOLLOW_FAILED, user, "Non è stato possibile eliminare l'utente dai following.").getMessage();
		
		return new MessageResponse(MessageCode.OK, user, "OK").getMessage();
	}
	
}
