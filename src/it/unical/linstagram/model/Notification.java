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
@Table(name = "notification")
public class Notification {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	@Cascade(value = CascadeType.ALL)
	@JoinColumn(name = "userfrom")
	private User userFrom;

	@ManyToOne
	@Cascade(value = CascadeType.ALL)
	@JoinColumn(name = "userto")
	private User UserTo;

	@ManyToOne
	@Cascade(value = CascadeType.ALL)
	@JoinColumn(name = "post")
	private Post post;

	@ManyToOne
	@Cascade(value = CascadeType.ALL)
	@JoinColumn(name = "comment")
	private Comment comment;

	@Column(nullable = false)
	private Calendar date;

	@Column(nullable = false)
	private NotificationType type;

	@Column(nullable = false)
	private boolean toSee;

	public Notification() {
	}

	public Notification(User userFrom, User userTo, Post post, Comment comment, NotificationType type) {
		this.userFrom = userFrom;
		this.UserTo = userTo;
		this.type = type;
		this.toSee = true;
		this.post = post;
		this.comment = comment;
		this.date = Calendar.getInstance();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(User userFrom) {
		this.userFrom = userFrom;
	}

	public User getUserTo() {
		return UserTo;
	}

	public void setUserTo(User userTo) {
		UserTo = userTo;
	}

	public NotificationType getType() {
		return type;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}

	public boolean isToSee() {
		return toSee;
	}

	public void setToSee(boolean toSee) {
		this.toSee = toSee;
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

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

}
