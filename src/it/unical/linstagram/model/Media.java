package it.unical.linstagram.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class Media{
	
	public enum Media_Type{
		VIDEO,IMAGE
	}
	
	@Column(nullable=false)
	private Media_Type type;
	
	@Column
	private String url;
	
	public Media() {
	}
	
	public Media(Media_Type type, String url) {
		this.url=url;
		this.type=type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	

	
}
