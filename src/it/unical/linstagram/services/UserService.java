package it.unical.linstagram.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.linstagram.helper.ProfilePreview;
import it.unical.linstagram.helper.UserManager;
import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.UserDAO;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	UserManager userManager;
	
	public List<ProfilePreview> getFollowers() {
		// TODO: to implement
		return null;
	}

	public List<ProfilePreview> getFollowing() {
		// TODO: to implement
		return null;
	}
	
	
	public boolean addFollowing(int myId, int userId) {
		//TODO: myID.addFollowing(userID);
		//		userID.addFollower(myId);
		return false;
	}
	
	
	public User getUser(String username) {
		return userDAO.getUserByUsername(username);
	}
	
	

}
