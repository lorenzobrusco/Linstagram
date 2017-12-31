package it.unical.linstagram.dto;

import it.unical.linstagram.model.User;

public class ViewerDTO extends UserDTO{

	private String photo;
	private String name;
	private String surname;
	
	public ViewerDTO(User user) {
		super(user);
		this.photo = user.getPhotoProfile();
		this.name = user.getName();
		this.surname = user.getSurname();
	}
	
	public String getName() {
		return name;
	}
	
	public String getPhoto() {
		return photo;
	}
	
	public String getSurname() {
		return surname;
	}
}
