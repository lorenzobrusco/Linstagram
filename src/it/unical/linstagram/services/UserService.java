package it.unical.linstagram.services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.linstagram.dto.UserDTO;
import it.unical.linstagram.dto.UserPrivateDTO;
import it.unical.linstagram.dto.UserPublicDTO;
import it.unical.linstagram.helper.ProfilePreview;
import it.unical.linstagram.helper.UserManager;
import it.unical.linstagram.model.Post;
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
	
	public UserDTO getOtherUser(User user, String usernameOther) {
		
		User userOther = userDAO.getUserByUsername(usernameOther);
		
		List<User> userFollowing = userDAO.getFollowingByUsername(user.getUsername());
		
		for (User u : userFollowing) {
			if (u.getId() == userOther.getId())
				return new UserPublicDTO(userOther, true);
		}
		
		if (userOther.isPrivateProfile())
			return new UserPrivateDTO(userOther);
		
		return new UserPublicDTO(userOther, false);
	}
	

	public List<User> getUsersList() {
		return userDAO.getAllUser();
	}
	
	
	public List<Post> getPostTaggedOfUser(String username) {
		return userDAO.getTaggedPostByUsername(username);
	}
	
	public void getListsUser(String username) {
		userDAO.inizializeLists(username);
	}
}
