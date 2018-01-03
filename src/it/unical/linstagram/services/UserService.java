package it.unical.linstagram.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.linstagram.dto.UserDTO;
import it.unical.linstagram.dto.UserPrivateDTO;
import it.unical.linstagram.dto.UserPublicDTO;
import it.unical.linstagram.dto.UserResearchDTO;
import it.unical.linstagram.helper.ProfilePreview;
import it.unical.linstagram.helper.UserManager;
import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.RequestFollow;
import it.unical.linstagram.model.Story;
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
	
	public boolean addFollowing(String usernameSession, String usernameToFollow) {
		
		User userSession = userDAO.getUserByUsername(usernameSession);
		User userToFollow = userDAO.getUserByUsername(usernameToFollow);
		
		userSession.getFollowings().add(userToFollow);
		userToFollow.getFollowers().add(userSession);
		
		if (modelDAO.update(userSession)) {
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
			return new UserPrivateDTO(userOther, false);
		
		return new UserPublicDTO(userOther, false);
	}
	

	public List<User> getUsersList() {
		return userDAO.getAllUser();
	}
	
	
	public List<Post> getPostTaggedOfUser(String username) {
		return userDAO.getTaggedPostByUsername(username);
	}

	public List<UserDTO> getFollowers(String username, String usernameSession) {
		List<User> followings = userDAO.getFollowingByUsername(usernameSession);
		List<User> followers = userDAO.getFollowerByUsername(username);
		
		List<UserDTO> followersDTO = new ArrayList<>();
		for (User user : followers) {
			boolean isFollow = false;
			for (User follow : followings) {
				if (user.getId() == follow.getId()) {
					isFollow = true;
					break;
				}
			}
			followersDTO.add(new UserPrivateDTO(user, isFollow));
		}
		
		return followersDTO;
	}
	
	public List<UserDTO> getFollowings(String username, String usernameSession) {
		if (username.equals(usernameSession)) {
			List<User> followings = userDAO.getFollowingByUsername(username);

			List<UserDTO> followingsDTO = new ArrayList<>();
			for (User user : followings) {
				followingsDTO.add(new UserPrivateDTO(user, true));
			}
			
			return followingsDTO;
		}
		
		List<User> followings = userDAO.getFollowingByUsername(username);
		List<User> followingsSession = userDAO.getFollowingByUsername(usernameSession);

		List<UserDTO> followingsDTO = new ArrayList<>();
		for (User user : followings) {
			boolean isFollow = false;
			for (User follow : followingsSession) {
				if (user.getId() == follow.getId()) {
					isFollow = true;
					break;
				}
			}
			followingsDTO.add(new UserPrivateDTO(user, isFollow));
		}
		
		return followingsDTO;
	}
	
	
	public void inizialiteList(Set<?> set) {
		userDAO.inizializeListUser(set);
	}

	public List<Post> getPostOfUser(String username) {
		return userDAO.getPostByUsername(username);
	}
	
	public boolean sendRequest(String usernameSession, String username) {
		User userSession = userDAO.getUserByUsername(usernameSession);
		User user = userDAO.getUserByUsername(username);
		
		RequestFollow request = new RequestFollow(userSession, user);
		if (modelDAO.save(request))
			return true;
		return false;
	}
	
	public boolean acceptRequest(String usernameSession, String username) {
		int id = userDAO.searchRequestFollow(username, usernameSession);
		if(addFollowing(username, usernameSession) && modelDAO.delete(RequestFollow.class, id))
			return true;
		return false;
	}
	
	public boolean rejectRequest (String usernameSession, String username) {
		int id = userDAO.searchRequestFollow(username, usernameSession);
		if(modelDAO.delete(RequestFollow.class, id))
			return true;
		return false;
	}
	
	public int searchRequestFollow (String usernameSession, String username) {
		int id = -1;
		id = userDAO.searchRequestFollow(username, usernameSession);
		return id;
	}
	
}
