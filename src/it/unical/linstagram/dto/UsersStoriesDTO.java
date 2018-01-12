package it.unical.linstagram.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unical.linstagram.model.Story;
import it.unical.linstagram.model.User;

public class UsersStoriesDTO {
	
	private String username;
	private String photoProfile;
	private boolean allSeen;
	private boolean loggedUser;
	private List<StoryDTO> stories;
	
	public UsersStoriesDTO(String username, String photoProfile,boolean loggedUser) {
		this.username = username;
		this.photoProfile = photoProfile;
		this.allSeen = true;
		this.loggedUser = loggedUser;
		stories = new ArrayList<StoryDTO>();
	}
	
	public void addStoryDTO(StoryDTO storyDTO) {
		if(!storyDTO.getViewed())
			allSeen = false;
		stories.add(storyDTO);
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPhotoProfile() {
		return photoProfile;
	}
	
	public List<StoryDTO> getStories() {
		return stories;
	}
	
	public boolean getAllSeen() {
		return allSeen;
	}
	public boolean isLoggedUser() {
		return loggedUser;
	}
}
