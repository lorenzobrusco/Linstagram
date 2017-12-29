package it.unical.linstagram.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unical.linstagram.model.Story;
import it.unical.linstagram.model.User;

public class FollowedUserStoriesDTO {
	
	private String username;
	private String photoProfile;
	private boolean allSeen;
	private List<StoryDTO> stories;
	
	public FollowedUserStoriesDTO(String username, String photoProfile) {
		this.username = username;
		this.photoProfile = photoProfile;
		this.allSeen = true;
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
	
	public boolean isAllSeen() {
		return allSeen;
	}
}
