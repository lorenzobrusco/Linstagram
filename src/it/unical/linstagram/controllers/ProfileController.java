package it.unical.linstagram.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import it.unical.linstagram.helper.UserManager;
import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;
import it.unical.linstagram.services.ProfileService;

@Controller
public class ProfileController {

	@Autowired
	private ProfileService profileService;
	
	@RequestMapping("profile")
	public String getSignInPage(HttpSession session, Model model) {
		if(UserManager.checkLogged(session)) {
			User user = (User) session.getAttribute("user");
			List<Post> postOfUser = profileService.getPostOfUser(user.getUsername());
			
			model.addAttribute("posts", postOfUser);
			return "profile";
		}
		return "redirect:/";
	}

	@RequestMapping("modifyProfile")
	public String getModifyProfile(HttpSession session) {
		if(UserManager.checkLogged(session))
			return "modify_profile";
		return "redirect:/";
	}

	@RequestMapping("taggedPhoto")
	public String getTaggedPhoto(HttpSession session, Model model) {
		if(UserManager.checkLogged(session)) {
			User user = (User) session.getAttribute("user");
			List<Post> postOfUser = profileService.getPostTaggedOfUser(user.getUsername());
			
			model.addAttribute("posts", postOfUser);
			return "fragment/profilePost";	//profilePost.jsp
		}
		return "redirect:/";
	}
	
}
