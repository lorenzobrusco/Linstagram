package it.unical.linstagram.dto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.unical.linstagram.model.Comment;
import it.unical.linstagram.model.Hashtag;
import it.unical.linstagram.model.Media;
import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;

public class PostDTO {

	private int id;
	private User user;
	private Calendar postDate;
	private String content;
	
	private List<Media> media = new ArrayList<Media>();
	
	private Set<User> likes = new HashSet<User>();
	
	private Set<UserDTO> tags = new HashSet<UserDTO>();
	
	private Set<Comment> comments = new HashSet<Comment>();
	
	private List<Hashtag> hashtags = new ArrayList<Hashtag>();
	
	private boolean likeUser;
	private boolean bookmarkUser;
	
	public PostDTO(Post post, boolean likeUser, boolean bookmarkUser) {
		this.id = post.getId();
		this.user = post.getUser();
		this.postDate = post.getPostDate();
		this.media = post.getMedia();
		this.likes = post.getLikes();
		this.content = post.getContent();
		
		for(User u: post.getTags())	{
			this.tags.add(new UserDTO(u));
		}
		
		this.comments = post.getComments();
		
		for(Hashtag h: post.getHashtags()) {
			this.hashtags.add(h);
		}
		
		this.likeUser = likeUser;
		this.bookmarkUser = bookmarkUser;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Calendar getPostDate() {
		return postDate;
	}

	public void setPostDate(Calendar postDate) {
		this.postDate = postDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Media> getMedia() {
		return media;
	}

	public void setMedia(List<Media> media) {
		this.media = media;
	}

	public Set<User> getLikes() {
		return likes;
	}

	public void setLikes(Set<User> likes) {
		this.likes = likes;
	}

	public Set<UserDTO> getTags() {
		return tags;
	}

	public void setTags(Set<UserDTO> tags) {
		this.tags = tags;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public List<Hashtag> getHashtags() {
		return hashtags;
	}

	public void setHashtags(List<Hashtag>hashtags) {
		this.hashtags = hashtags;
	}
	
	public boolean isLikeUser() {
		return likeUser;
	}
	
	public void setLikeUser(boolean likeUser) {
		this.likeUser = likeUser;
	}
	
	public boolean isBookmarkUser() {
		return bookmarkUser;
	}
	
	public void setBookmarkUser(boolean bookmarkUser) {
		this.bookmarkUser = bookmarkUser;
	}
	
}
