package it.unical.linstagram.dto;

import it.unical.linstagram.model.User;

public class UserResearchDTO extends UserDTO {

	protected String name;
	protected String surname;
	protected String photoProfile;
	
	public UserResearchDTO(User user) {
		super(user);
		// TODO Auto-generated constructor stub
		this.name = user.getName();
		this.surname = user.getSurname();
		this.photoProfile = user.getPhotoProfile();
	}

	public String getPhotoProfile() {
		return photoProfile;
	}

	public void setPhotoProfile(String photoProfile) {
		this.photoProfile = photoProfile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
}
