package it.unical.linstagram.dto;

import java.util.ArrayList;
import java.util.List;

public class StoryViewerDTO {

	private int id;
	List<UserDTO> users;
	
	public StoryViewerDTO(int idStory) {
		this.id=idStory;
		users = new ArrayList<>();
	}
	
	public void addViewer(UserDTO user) {
		users.add(user);
	}
	
	public int getId() {
		return id;
	}
	
	public List<UserDTO> getUsers() {
		return users;
	}
}
