package it.unical.linstagram.dto;

import java.util.List;

import it.unical.linstagram.model.Media;
import it.unical.linstagram.model.Post;

public class PostPreviewDTO {
	
	private int id;
	private List<Media> media;
	private int nLikes;
	private int nComments;
	
	public PostPreviewDTO(Post post, int nLikes, int nComments) {
		this.id = post.getId();
		this.nLikes = nLikes;
		this.nComments = nComments;
		this.media = post.getMedia();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Media> getMedia() {
		return media;
	}
	public void setMedia(List<Media> media) {
		this.media = media;
	}
	public int getnLikes() {
		return nLikes;
	}
	public void setnLikes(int nLikes) {
		this.nLikes = nLikes;
	}
	public int getnComments() {
		return nComments;
	}
	public void setnComments(int nComments) {
		this.nComments = nComments;
	} 
	
	
}
