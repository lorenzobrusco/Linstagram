package it.unical.linstagram.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import it.unical.linstagram.helper.UserManager;

@Controller
public class HomePageController {
	
	@RequestMapping("/index")
	public String homePageController(HttpSession session) {
		if(UserManager.checkLogged(session))
			return "index";
		return "redirect:/";
	}
}
	