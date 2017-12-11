package it.unical.linstagram.domain;

import java.util.Date;

public class Comment {

	private int id;

	private String content;
	private User user;
	private Post post;
	private Date date;

	public Comment() {}
	
	public Comment(String content, User user, Post post, Date date) {
		this.content = content;
		this.user = user;
		this.post = post;
		this.date = date;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
