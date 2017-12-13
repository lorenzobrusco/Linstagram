package it.unical.linstagram.model;

import java.util.Date;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

public class Post {

	private int id;
	
	@ManyToOne
	@JoinColumn(name = "user")
	@Cascade(value=CascadeType.SAVE_UPDATE)
	private User user;
	
	private List<User> likes;
	private List<Comment> comments;
	private List<Hashtag> hashtags;
	private List<User> tags;
	private Media content;
	private Date postDate;
	private String description;

	public Post() {}

	public Post(User user, List<User> likes, List<Comment> comments, List<Hashtag> hashtags, List<User> tags,
			Media content, Date postDate, String description) {
		this.user = user;
		this.likes = likes;
		this.comments = comments;
		this.hashtags = hashtags;
		this.tags = tags;
		this.content = content;
		this.postDate = postDate;
		this.description = description;
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

	public List<User> getLikes() {
		return likes;
	}

	public void setLikes(List<User> likes) {
		this.likes = likes;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Hashtag> getHashtags() {
		return hashtags;
	}

	public void setHashtags(List<Hashtag> hashtags) {
		this.hashtags = hashtags;
	}

	public List<User> getTags() {
		return tags;
	}

	public void setTags(List<User> tags) {
		this.tags = tags;
	}

	public Media getContent() {
		return content;
	}

	public void setContent(Media content) {
		this.content = content;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
