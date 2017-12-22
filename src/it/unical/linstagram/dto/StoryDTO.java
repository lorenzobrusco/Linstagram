package it.unical.linstagram.dto;

import it.unical.linstagram.model.Media.Media_Type;
import it.unical.linstagram.model.Story;

public class StoryDTO {
	private int id;
	private String url;
	private String type;
	public boolean viewed;
	
	public StoryDTO(Story story, boolean viewed) {
		id=story.getId();
		url = story.getMedia().getUrl();
		type = convertType(story.getMedia().getType());
		this.viewed = viewed;
	}
	
	private String convertType(Media_Type type) {
		if(type==Media_Type.IMAGE)
			return "photo";
		else
			return "video";
	}
	public String getUrl() {
		return url;
	}
	
	public String getType() {
		return type;
	}
	
	public boolean getViewed() {
		return viewed;
	}
	
	public int getId() {
		return id;
	}
}
