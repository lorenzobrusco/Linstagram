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

@Entity
@Table(name = "notification")
public class Notification {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	@JoinColumn(name = "userfrom")
	private User userFrom;

	@ManyToOne
	@JoinColumn(name = "userto")
	private User userTo;

	@ManyToOne
	@JoinColumn(name = "post")
	private Post post;

	@ManyToOne
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
		this.userTo = userTo;
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
		return userTo;
	}

	public void setUserTo(User userTo) {
		this.userTo = userTo;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		final Notification notification = (Notification) obj;
		if (!(notification.userTo == this.userTo))
			return false;
		if (!(notification.userFrom == this.userFrom))
			return false;
		if (!(notification.toSee == this.toSee))
			return false;
		if (!(notification.post == this.post))
			return false;
		if (!(notification.comment== this.comment))
			return false;
		if (!(notification.type== this.type))
			return false;
		return true;
	}

}
