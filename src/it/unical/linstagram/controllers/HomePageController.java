package it.unical.linstagram.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.unical.linstagram.helper.UserManager;

@Controller
public class HomePageController {
	
	@RequestMapping("/index")
	public String homePageController(HttpSession session) {
		if(UserManager.checkLogged(session))
			return "index";
		return "redirect:/";
	}
	
	
	
	@RequestMapping("/createPost")
	public String CreatePost(@RequestParam String postDescription,HttpSession session) {
		//TODO call service that parse description, get file and create post
		System.out.println("Crea post:"+postDescription);
		return "redirect:/index";
	}
}
	