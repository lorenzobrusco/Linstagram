package it.unical.linstagram.dto;

import it.unical.linstagram.model.Hashtag;
import it.unical.linstagram.model.User;

public class ResearchDTO {

	private String title;
	private String subtitle;
	private String iconUrl;
	private String type;
	
	public ResearchDTO(User user) {
		this.title = user.getUsername();
		
		String subtitle = "";
		if (user.getName() != null )
		{
			subtitle = subtitle+user.getName();
			if (user.getSurname() != null)
				subtitle = subtitle+" "+ user.getSurname();
		}
		else if (user.getSurname() != null)
			subtitle = subtitle+user.getSurname();
		
		this.subtitle = subtitle;
		
		this.iconUrl = user.getPhotoProfile();
		this.setType("user");
		
	}
	
	public ResearchDTO(Hashtag hashtag) {
		this.title = "#"+ hashtag.getHashtag();
		this.subtitle = hashtag.getCount()+ " posts";
		this.iconUrl = "resources/images/Hashtag.png";
		this.setType("hashtag");
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
