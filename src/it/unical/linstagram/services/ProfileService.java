package it.unical.linstagram.services;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.linstagram.model.Gender;
import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.HibernateUtil;
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

	
	public boolean changeName(User user, String name) {
		user.setName(name);
		if (modelDAO.merge(user))
			return true;
		return false;
	}
	
	public boolean changeSurname(User user, String surname) {
		user.setSurname(surname);
		if (modelDAO.merge(user))
			return true;
		return false;
	}
	
	public boolean changeUsername(User user, String username) {
		User userFound = userDAO.getUserByUsername(username);
		if (userFound == null) {
			user.setUsername(username);
			if (modelDAO.merge(user))
				return true;
			return false;
		}
		
		return false;		
	}

	public boolean changeEmail(User user, String email) {
		User userFound = userDAO.getUserByEmail(email);
		if (userFound == null) {
			user.setEmail(email);
			if (modelDAO.merge(user))
				return true;
			return false;
		}
		
		return false;	
	}

	public boolean changeGender(User user, String gender) {
		user.setGender(Gender.values()[Integer.parseInt(gender)-1]);
		if (modelDAO.merge(user))
			return true;
		return false;
	}

	public boolean changeBiography(User user, String bio) {
		user.setBiography(bio);
		if (modelDAO.merge(user))
			return true;
		return false;
	}

	public boolean changeDate(User user, Calendar date) {
		user.setBirthdate(date);
		if (modelDAO.merge(user))
			return true;
		return false;
	}

	public boolean changePrivateField(User user, String privateCheck) {
		if (privateCheck == "true")
			user.setPrivateProfile(true);
		else 
			user.setPrivateProfile(false);
		
		if (modelDAO.merge(user))
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
	
}
