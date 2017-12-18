package it.unical.linstagram.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import it.unical.linstagram.helper.UserManager;

@Controller
public class ProfileController {

	@RequestMapping("profile")
	public String getSignInPage(HttpSession session) {
		if(UserManager.checkLogged(session))
			return "profile";
		return "redirect:/";
	}

	@RequestMapping("modifyProfile")
	public String getModifyProfile(HttpSession session) {
		if(UserManager.checkLogged(session))
			return "modify_profile";
		return "redirect:/";
	}

}
