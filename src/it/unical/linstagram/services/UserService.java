package it.unical.linstagram.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.linstagram.dto.UserDTO;
import it.unical.linstagram.dto.UserPrivateDTO;
import it.unical.linstagram.dto.UserPublicDTO;
import it.unical.linstagram.helper.ProfilePreview;
import it.unical.linstagram.helper.UserManager;
import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.ModelDAO;
import it.unical.linstagram.persistence.UserDAO;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private ModelDAO modelDAO;
	
	UserManager userManager;
	
	public boolean addFollowing(String usernameSession, String usernameToFollow, User user) {
		
		User userSession = userDAO.getUserByUsername(usernameSession);
		User userToFollow = userDAO.getUserByUsername(usernameToFollow);
		
		userSession.getFollowings().add(userToFollow);
		userToFollow.getFollowers().add(userSession);
		
		if (modelDAO.update(userSession)) {
			user.getFollowings().add(userToFollow);
			return true;
		}
		return false;
	}
	
	public boolean removeFollowing(String usernameSession, String usernameToFollow, User user) {

		
		User userSession = userDAO.getUserByUsername(usernameSession);
		User userToFollow = userDAO.getUserByUsername(usernameToFollow);
		
		userSession.getFollowings().remove(userToFollow);
		userToFollow.getFollowers().remove(userSession);
		
		if (modelDAO.update(userSession)) {
			user.getFollowings().remove(userToFollow);
			return true;
		}
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
	
	/**
	 * Initialize the list of the user
	 * @param username 
	 */
//	public User getListsUser(String username) {
//		
//		
//		User user = userDAO.getUserByUsername(username);
//		for (User follow : user.getFollowings()) {
//			
//		}
//		
//		
//		return null;
//	}
	
	public List<UserDTO> getFollowers(String username) {
		List<User> followers = userDAO.getFollowerByUsername(username);
		
		List<UserDTO> followersDTO = new ArrayList<>();
		for (User user : followers) {
			followersDTO.add(new UserPrivateDTO(user));
		}
		
		return followersDTO;
	}
	
	public List<UserDTO> getFollowings(String username) {
		List<User> followings = userDAO.getFollowingByUsername(username);
		
		List<UserDTO> followingsDTO = new ArrayList<>();
		for (User user : followings) {
			followingsDTO.add(new UserPrivateDTO(user));
		}
		
		return followingsDTO;
	}
	
	
	public void inizialiteList(Set<Post> set) {
		userDAO.inizializeListUser(set);
	}
	
}
