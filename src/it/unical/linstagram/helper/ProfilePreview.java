package it.unical.linstagram.helper;

import it.unical.linstagram.domain.Media;
import it.unical.linstagram.domain.User;

public class ProfilePreview {
	
	private Media photoProfile;
	private String username;
	
	public ProfilePreview(User user) {
		this.photoProfile = user.getPhotoProfile();
		this.username = user.getUsername();
	}
	public Media getPhotoProfile() {
		return photoProfile;
	}

	public String getUsername() {
		return username;
	}

}
