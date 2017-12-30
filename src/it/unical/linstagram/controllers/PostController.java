package it.unical.linstagram.controllers;

import java.util.Calendar;
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
	
	@RequestMapping("addLike")
	@ResponseBody
	public String insertLike(HttpSession session, Model model, @RequestParam("postID") int idPost) {
		User user = (User) session.getAttribute("user");
		if (postService.insertLike(idPost, user.getUsername()))
			return new MessageResponse(MessageCode.OK, user, "OK").getMessage();
		
		return new MessageResponse(MessageCode.FAILED, user, "Non è stato potuto inserire il like.").getMessage();
	}
	
	@RequestMapping("removeLike")
	@ResponseBody
	public String removeLike(HttpSession session, Model model, @RequestParam("postID") int idPost) {
		User user = (User) session.getAttribute("user");
		if (postService.removeLike(user.getUsername(), idPost))
			return new MessageResponse(MessageCode.OK, user, "OK").getMessage();
		
		return new MessageResponse(MessageCode.FAILED, user, "Non è stato potuto inserire il like.").getMessage();
	}
	
	@RequestMapping("addBookmark")
	@ResponseBody
	public String insertBookmark(HttpSession session, Model model, @RequestParam("postID") int idPost) {
		User user = (User) session.getAttribute("user");
		User userDB = postService.insertBookmark(user.getUsername(), idPost);
		if (userDB != null) {
			session.setAttribute("user", userDB);
			return new MessageResponse(MessageCode.OK, user, "OK").getMessage();
		}
		return new MessageResponse(MessageCode.FAILED, user, "Non è stato potuto inserire il like.").getMessage();
	}
	
	@RequestMapping("removeBookmark")
	@ResponseBody
	public String removeBookmark(HttpSession session, Model model, @RequestParam("postID") int idPost) {
		User user = (User) session.getAttribute("user");
		User userDB = postService.removeBookmark(user.getUsername(), idPost);
		if (userDB != null) {
			session.setAttribute("user", userDB);
			return new MessageResponse(MessageCode.OK, user, "OK").getMessage();
		}
		return new MessageResponse(MessageCode.FAILED, user, "Non è stato potuto inserire il like.").getMessage();
	}
	
	
	@RequestMapping("addComment")
	@ResponseBody
	public String insertComment(HttpSession session, Model model, @RequestParam("postID") int idPost, @RequestParam("comment") String comment) {
		User user = (User) session.getAttribute("user");
		if (!comment.equals(""))
			if (postService.insertComment(idPost, user.getUsername() ,comment, Calendar.getInstance()))
				return new MessageResponse(MessageCode.OK, user, "OK").getMessage();
		
		return new MessageResponse(MessageCode.FAILED, user, "Non è stato potuto inserire il like.").getMessage();
	}
	
	
	@RequestMapping("getLikes")
	public String getLikes(HttpSession session, Model model, @RequestParam("post") int idPost) {

		List<UserDTO> users = postService.getLikesOfPost(idPost);
		model.addAttribute("userLike", users);
		
		return "fragment/indexFragment/body/body_likes";
	}
	
	
	@RequestMapping("getPost")
	public String getPost(HttpSession session, Model model, @RequestParam("post") int idPost) {

		Post post = postService.getPost(idPost);
		model.addAttribute("post", post);
		
		return "fragment/userProfileFragment/body/post";
	}
	
}
