package it.unical.linstagram.dto;

import it.unical.linstagram.model.User;

public class UserPrivateDTO extends UserDTO{

	protected String name;
	protected String surname;
	
	protected boolean privateProfile;

	protected String photoProfile;
	
	protected boolean followed;
	protected boolean request_send;
	protected boolean request_received;
	
	public UserPrivateDTO (User user, boolean followed, boolean request_send, boolean request_received) {
		super(user);
		this.name = user.getName();
		this.surname = user.getSurname();
		
		this.privateProfile = user.isPrivateProfile();
		this.photoProfile = user.getPhotoProfile();
		
		this.followed = followed;
		this.request_send = request_send;
		this.request_received = request_received;
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
	
	public boolean isFollowed() {
		return followed;
	}

	public boolean isRequest_send() {
		return request_send;
	}

	public boolean isRequest_received() {
		return request_received;
	}
	
}
