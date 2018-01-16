package it.unical.linstagram.model;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="story")
public class Story {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="user",nullable=false)
	private User user;
	
	@Column(nullable=false)
	private Calendar creationDate;
	
	@Embedded
	@Cascade(value=CascadeType.ALL)
	@AttributeOverrides(value = {
	            @AttributeOverride(name = "type", 
	            		column = @Column(name = "media_type")),
	            @AttributeOverride(name = "url", column = @Column(name = "media_url"))})
	private Media media;
	
	@ManyToMany (fetch=FetchType.EAGER)
	@JoinTable(name="viewed_story",
			joinColumns=@JoinColumn(name="story_id"),
			inverseJoinColumns=@JoinColumn(name="user_id"))
	private Set<User> viewers = new HashSet<User>();
	
	public Story() {
	}
	
	public Story(User user, Media media) 
	{
		this.user = user;
		this.media = media;
		this.creationDate = Calendar.getInstance();
	}
	
	public void addViewer(User user) {
		viewers.add(user);
	}
	
	public boolean isAViewer(User user) {
		return viewers.contains(user);
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
	public Calendar getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}
	public Media getMedia() {
		return media;
	}
	public void setMedia(Media media) {
		this.media = media;
	}
	public Set<User> getViewers() {
		return viewers;
	}
	public void setViewers(Set<User> viewers) {
		this.viewers = viewers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Story other = (Story) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
