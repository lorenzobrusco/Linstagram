package it.unical.linstagram.dto;

import it.unical.linstagram.model.User;

public class UserDTO {

	protected int id;
	protected String username;
	
	
	public UserDTO(User user) {
		super();
		this.id = user.getId();
		this.username = user.getUsername();
	}


	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}
	
}
