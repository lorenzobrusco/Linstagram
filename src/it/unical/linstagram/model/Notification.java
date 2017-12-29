package it.unical.linstagram.model;

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

	@Column
	private String type;

	@Column
	private boolean toSee;

	public Notification() {
	}

	public Notification(User userFrom, User userTo, String type, boolean toSee) {
		super();
		this.userFrom = userFrom;
		UserTo = userTo;
		this.type = type;
		this.toSee = toSee;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isToSee() {
		return toSee;
	}

	public void setToSee(boolean toSee) {
		this.toSee = toSee;
	}

}
