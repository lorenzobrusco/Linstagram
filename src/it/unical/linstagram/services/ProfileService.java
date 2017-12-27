package it.unical.linstagram.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.linstagram.model.Gender;
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
	
	
	public List<Post> getPostOfUser(String username) {
		return userDAO.getPostByUsername(username);
	}
	
	public List<Post> getPostTaggedOfUser(String username) {
		return userDAO.getTaggedPostByUsername(username);
	}

	public List<Post> getBookmarkOfUser(String username) {
		return userDAO.getBookmarksByUsername(username);
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

	
}
