package it.unical.linstagram.controllers;

import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.unical.linstagram.helper.MessageResponce;
import it.unical.linstagram.helper.UserManager;
import it.unical.linstagram.model.Comment;
import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;
import it.unical.linstagram.services.MediaService;
import it.unical.linstagram.services.MessageCode;
import it.unical.linstagram.services.PostService;
import it.unical.linstagram.services.UserService;

@Controller
public class PostController {

	@Autowired
	private PostService postService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/like")
	@ResponseBody
	public String insertLike(HttpSession session, Model model, @RequestParam("postID") int idPost) {
		User user = (User) session.getAttribute("user");
		if (postService.insertLike(idPost, user))
			return new MessageResponce(MessageCode.OK, user, "OK").getMessage();
		
		return new MessageResponce(MessageCode.FAILED, user, "Non è stato potuto inserire il like.").getMessage();
	}
	
	@RequestMapping("/bookmark")
	@ResponseBody
	public String insertBookmark(HttpSession session, Model model, @RequestParam("postID") int idPost) {
		User user = (User) session.getAttribute("user");
		if (postService.insertBookmark(user, idPost))
			return new MessageResponce(MessageCode.OK, user, "OK").getMessage();
		
		return new MessageResponce(MessageCode.FAILED, user, "Non è stato potuto inserire il like.").getMessage();
	}
	
	
//	@RequestMapping("comment")
//	@ResponseBody
//	public String insertComment(HttpSession session, Model model, @RequestParam("postID") int idPost, @RequestParam("comment") String comment) {
//		User user = (User) session.getAttribute("user");
//		Post post = postService.getPost(idPost);
//		System.out.println("POST "+post);
//		Comment c = new Comment(comment, user, post, Calendar.getInstance());
//		
//		if (postService.insertComment(idPost, c))
//			return new MessageResponce(MessageCode.OK, user, "OK").getMessage();
//		
//		return new MessageResponce(MessageCode.FAILED, user, "Non è stato potuto inserire il like.").getMessage();
//	}
//	
}
