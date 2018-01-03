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
@Table(name="requestFollow")
public class RequestFollow {

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
	private User userTo;

	public RequestFollow() {
		super();
	}

	public RequestFollow(User userFrom, User userTo) {
		super();
		this.userFrom = userFrom;
		this.userTo = userTo;
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
	
}
