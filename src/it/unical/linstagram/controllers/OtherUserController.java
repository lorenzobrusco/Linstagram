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
import it.unical.linstagram.helper.MessageResponse;
import it.unical.linstagram.helper.UserManager;
import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;
import it.unical.linstagram.services.MessageCode;
import it.unical.linstagram.services.NotificationService;
import it.unical.linstagram.services.UserService;

@Controller
public class OtherUserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private NotificationService notificationService;
	
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
		List<Post> postOfUser = userService.getPostOfUser(usernameOther);
		model.addAttribute("userPublic", userDTO);
		model.addAttribute("posts", postOfUser);
		
		if (userService.searchRequestFollow(user.getUsername(), usernameOther) != -1) 
			model.addAttribute("request_send", true);
		else 
			model.addAttribute("request_send", false);
		
		if (userService.searchRequestFollow(usernameOther, user.getUsername()) != -1)
			model.addAttribute("request_received", true);
		else
			model.addAttribute("request_received", false);
		return "otherUserProfile";
	}
	
	@RequestMapping("sendRequest")
	@ResponseBody
	public String sendRequest(HttpSession session, Model model, @RequestParam("username") String username) {
		User user = (User) session.getAttribute("user");
		System.out.println("here");
		if (!userService.sendRequest(user.getUsername(), username))
			return new MessageResponse(MessageCode.FAILED, user, "Non è stato possibile inoltrare la richiesta.").getMessage();
		notificationService.generateFollowNotification(user, username);
		return new MessageResponse(MessageCode.OK, user, "OK").getMessage();
	}
	
	@RequestMapping("acceptRequest")
	@ResponseBody
	public String acceptRequest(HttpSession session, Model model, @RequestParam("username") String username) {
		User user = (User) session.getAttribute("user");
		
		if (!userService.acceptRequest(user.getUsername(), username))
			return new MessageResponse(MessageCode.FAILED, user, "Non è stato possibile inoltrare la richiesta.").getMessage();
		
		return new MessageResponse(MessageCode.OK, user, "OK").getMessage();
	}
	
	@RequestMapping("rejectRequest")
	@ResponseBody
	public String rejectRequest(HttpSession session, Model model, @RequestParam("username") String username) {
		User user = (User) session.getAttribute("user");
		
		if (!userService.rejectRequest(user.getUsername(), username))
			return new MessageResponse(MessageCode.FAILED, user, "Non è stato possibile inoltrare la richiesta.").getMessage();
//		
		return new MessageResponse(MessageCode.OK, user, "OK").getMessage();
	}
	
	@RequestMapping("getFollowings")
	public String getFollowings(HttpSession session, Model model, @RequestParam("username") String username) {
		User user = (User) session.getAttribute("user");
		List<UserDTO> users = userService.getFollowings(username, user.getUsername());

		model.addAttribute("followings", users);
		
		if (username.equals(user.getUsername()))
			return "fragment/followFragment/body/body_follow_user_session";
		
		return "fragment/followFragment/body/body_following";
	}
	
	@RequestMapping("getFollowers")
	public String getFollowers(HttpSession session, Model model, @RequestParam("username") String username) {
		User user = (User) session.getAttribute("user");
		
		List<UserDTO> users = userService.getFollowers(username, user.getUsername());
		model.addAttribute("followers", users);
		
		return "fragment/followFragment/body/body_follower";
	}
	
	@RequestMapping("followUser")
	@ResponseBody
	public String followUser(HttpSession session, Model model, @RequestParam("username") String usernameToFollow) {
		User user = (User) session.getAttribute("user");
		
		if (!userService.addFollowing(user.getUsername(), usernameToFollow))
			return new MessageResponse(MessageCode.FOLLOW_FAILED, user, "Non è stato possibile inserire l'utente come following.").getMessage();
		notificationService.generateFollowNotification(user, usernameToFollow);
		return new MessageResponse(MessageCode.OK, user, "OK").getMessage();
	}
	
	@RequestMapping("unfollowUser")
	@ResponseBody
	public String unfollowUser(HttpSession session, Model model, @RequestParam("username") String usernameToFollow) {

		User user = (User) session.getAttribute("user");
		if (!userService.removeFollowing(user.getUsername(), usernameToFollow, user))
			return new MessageResponse(MessageCode.UNFOLLOW_FAILED, user, "Non è stato possibile eliminare l'utente dai following.").getMessage();
		
		return new MessageResponse(MessageCode.OK, user, "OK").getMessage();
	}
	
}
