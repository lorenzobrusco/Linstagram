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

	
	public boolean changeName(String username, String name) {
		User user = userDAO.getUserByUsername(username);
		user.setName(name);
		if(modelDAO.update(user))
			return true;
		return false;
	}
	
	public boolean changeSurname(String username, String surname) {
		User user = userDAO.getUserByUsername(username);
		user.setSurname(surname);
		if(modelDAO.update(user))
			return true;
		return false;
	}
	
	public boolean changeUsername(String usernameUser, String username) {
		User userFound = userDAO.getUserByUsername(username);
		if (userFound == null) {
			User user = userDAO.getUserByUsername(usernameUser);
			user.setUsername(usernameUser);
			if(modelDAO.update(user))
				return true;
		}
		
		return false;		
	}
	
	public boolean changeEmail(String username, String email) {
		User userFound = userDAO.getUserByEmail(email);
		if (userFound == null) {
			User user = userDAO.getUserByUsername(username);
			user.setUsername(username);
			if(modelDAO.update(user))
				return true;
		}
		return false;	
	}

	public boolean changeGender(String username, String gender) {
		User user = userDAO.getUserByUsername(username);
		user.setGender(Gender.values()[Integer.parseInt(gender)-1]);
		if(modelDAO.update(user))
			return true;
		return false;
	}
	
	public boolean changeBirthday(String username, Calendar cal) {
		User user = userDAO.getUserByUsername(username);
		user.setBirthdate(cal);
		if(modelDAO.update(user))
			return true;
		return false;
	}
	
	public boolean changeBiography(String username, String biography) {
		User user = userDAO.getUserByUsername(username);
		user.setBiography(biography);
		if(modelDAO.update(user))
			return true;
		return false;
	}
	
	public boolean changePrivate(String username, String privateCheck) {
		User user = userDAO.getUserByUsername(username);
		if (privateCheck == "true")
			user.setPrivateProfile(true);
		else 
			user.setPrivateProfile(false);
		if(modelDAO.update(user))
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
