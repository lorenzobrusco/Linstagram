package it.unical.linstagram.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.unical.linstagram.dto.PostDTO;
import it.unical.linstagram.model.Hashtag;
import it.unical.linstagram.model.User;
import it.unical.linstagram.services.HashtagService;
import it.unical.linstagram.services.PostService;

@Controller
public class HashtagController {
	
	@Autowired
	private PostService postService;

	@Autowired
	private HashtagService hashtagService;

	@RequestMapping("/otherHashtags")
	public String otherHashtag(@RequestParam int last,@RequestParam long time,HttpSession session,Model model) {
		User loggedUser = (User) session.getAttribute("user");
		
		List<PostDTO> posts =  null;
		posts = postService.getPostsbyHashtag(loggedUser,
					(String)session.getAttribute("hashtagPost"),last);
		
		model.addAttribute("posts", posts);
		return "fragment/post";
	}
	
	@RequestMapping("/hashtags")
	public String hashtagPosts(@RequestParam String hashtag, HttpSession session, Model model) {
		
		List<PostDTO> posts = postService.getPostsbyHashtag((User)session.getAttribute("user"), hashtag,0);
		model.addAttribute("posts", posts);
		session.setAttribute("hashtagPost",hashtag);
		
		Hashtag ht = hashtagService.getHashtag(hashtag);
		model.addAttribute("hashtag",ht);
		
		return "hashtags";


	}
}
