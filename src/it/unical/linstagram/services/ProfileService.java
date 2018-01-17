package it.unical.linstagram.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.linstagram.dto.PostPreviewDTO;
import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.ModelDAO;
import it.unical.linstagram.persistence.UserDAO;

@Service
public class ProfileService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private ModelDAO modelDAO;
	
	
	public List<PostPreviewDTO> getPostOfUser(String username, int last) {
		List<Post> posts = userDAO.getPostByUsername(username,last);
		List<PostPreviewDTO> postDTOs = new ArrayList<>();
		for(Post post: posts) {
			postDTOs.add(
					new PostPreviewDTO(post,
							modelDAO.getCount("p.likes", Post.class, "p.id="+post.getId()),
					modelDAO.getCount("p.comments", Post.class, "p.id="+post.getId())));
		}
		return postDTOs;
	}
	
	public List<PostPreviewDTO> getPostTaggedOfUser(String username,int last) {
		List<Post> posts = userDAO.getTaggedPostByUsername(username, last);
		List<PostPreviewDTO> postDTOs = new ArrayList<>();
		for(Post post: posts) {
			postDTOs.add(
					new PostPreviewDTO(post,
							modelDAO.getCount("p.likes", Post.class, "p.id="+post.getId()),
					modelDAO.getCount("p.comments", Post.class, "p.id="+post.getId())));
		}
		return postDTOs;
	}

	public List<PostPreviewDTO> getBookmarkOfUser(String username,int last) {
		List<Post> posts = userDAO.getBookmarksByUsername(username,last);
		List<PostPreviewDTO> postDTOs = new ArrayList<>();
		for(Post post: posts) {
			postDTOs.add(
					new PostPreviewDTO(post,
							modelDAO.getCount("p.likes", Post.class, "p.id="+post.getId()),
					modelDAO.getCount("p.comments", Post.class, "p.id="+post.getId())));
		}
		return postDTOs;
	}

	
	public boolean changeUsername(String username) {
		User userFound = userDAO.getUserByUsername(username);
		if (userFound == null) 
			return true;
		return false;		
	}
	
	public boolean changeEmail(String email) {
		User userFound = userDAO.getUserByEmail(email);
		if (userFound == null) 
			return true;
		return false;	
	}
	
	public String getPassword(String username) {
		return userDAO.getPasswordByUsername(username);
	}
	
	public boolean changePassword(User user, String password) {
		user.setPassword(password);
		if (modelDAO.merge(user))
			return true;
		return false;
	}
	
	public boolean uploadPhotoProfile(User user) {
		if (modelDAO.merge(user))
			return true;
		return false;
	}
	
	public boolean updateUser(User user) {
		if (modelDAO.merge(user))
			return true;
		return false;
	}
	
	public int getPostCount(int userId) {
		return modelDAO.getCount("u.posts", User.class, "u.id="+userId);
	}

	
}
