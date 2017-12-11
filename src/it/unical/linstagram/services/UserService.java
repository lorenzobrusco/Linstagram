package it.unical.linstagram.services;

import java.util.List;

import it.unical.linstagram.domain.MyProfile;
import it.unical.linstagram.domain.Profile;
import it.unical.linstagram.helper.ProfilePreview;

public class UserService {

	public List<ProfilePreview> getFollowers() {
		// TODO: to implement
		return null;
	}

	public List<ProfilePreview> getFollowing() {
		// TODO: to implement
		return null;
	}

	public MyProfile getMyProfile(int id) {
		//TODO: get my profile
		return null;
	}

	public Profile getUserProfile(int id) {
		//TODO: return the prefile of another user
		return null;
	}

	public boolean addFollowing(int myId, int userId) {
		//TODO: myID.addFollowing(userID);
		//		userID.addFollower(myId);
		return false;
	}
	
	
	

}
