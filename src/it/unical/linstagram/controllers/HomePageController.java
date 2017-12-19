package it.unical.linstagram.controllers;

import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.unical.linstagram.helper.UserManager;
import it.unical.linstagram.model.Comment;
import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;
import it.unical.linstagram.services.PostService;
import it.unical.linstagram.services.UserService;

@Controller
public class HomePageController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/index")
	public String homePageController(HttpSession session, Model model) {
		if(UserManager.checkLogged(session)) {
			model.addAttribute("posts", postService.getPosts());
			return "index";
		}
		return "redirect:/";
	}

	
	@RequestMapping("/createPost")
	public String CreatePost(@RequestParam String postDescription,HttpSession session) {
		//TODO call service that parse description, get file and create post
		System.out.println("Crea post:"+postDescription);
		return "redirect:/index";
	}

	@RequestMapping("/like")
	public String insertLike(HttpSession session, Model model, @RequestParam int idPost) {
		if(UserManager.checkLogged(session)) {
			User user = userService.getUser((String) session.getAttribute("username"));
			postService.insertLike(idPost, user);
			return "redirect:index";
		}
		return "redirect:index";
	}
	
	@RequestMapping("/comment")
	public String insertComment(HttpSession session, Model model, @RequestParam int idPost, @RequestParam String comment) {
		if(UserManager.checkLogged(session)) {
			User user = userService.getUser((String) session.getAttribute("username"));
			Post post = postService.getPost(idPost);
			System.out.println("POST "+post);
			Comment c = new Comment(comment, user, post, Calendar.getInstance());
			
			postService.insertComment(idPost, c);
			
			return "redirect:index";
		}
		return "redirect:index";
	}
	
	
}
	