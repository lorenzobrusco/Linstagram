package it.unical.linstagram.dto;

import it.unical.linstagram.model.User;

public class UserPrivateDTO extends UserDTO{

	protected String name;
	protected String surname;
	
	protected boolean privateProfile;

	protected String photoProfile;

	
	public UserPrivateDTO (User user) {
		super(user);
		this.name = user.getName();
		this.surname = user.getSurname();
		
		this.privateProfile = user.isPrivateProfile();
		this.photoProfile = user.getPhotoProfile();
		
	}
	

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}
	
	public boolean isPrivateProfile() {
		return privateProfile;
	}

	public String getPhotoProfile() {
		return photoProfile;
	}
	
	
	
	
}
