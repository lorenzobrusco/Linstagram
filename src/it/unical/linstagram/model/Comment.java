package it.unical.linstagram.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="comment")
public class Comment {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(nullable=false)
	private String content;

	@ManyToOne
	@Cascade(value=CascadeType.ALL)
	@JoinColumn(name="user")
	private User user;
	
	@ManyToOne
	@Cascade(value=CascadeType.ALL)
	@JoinColumn(name="post")
	private Post post;
	
	@Column(nullable=false)
	private Calendar date;

	public Comment() {}
	
	public Comment(String content, User user, Post post, Calendar date) {
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

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

}
