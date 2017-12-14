package it.unical.linstagram.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="post")
public class Post {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "user")
	@Cascade(value=CascadeType.SAVE_UPDATE)
	private User user;
	
	@Column(nullable=false)
	private Calendar postDate;
	
	@Column
	private String content;

//	private Media media;
	
	@ManyToMany
	@Cascade(value=CascadeType.ALL)
    @JoinTable(name="likes",
               joinColumns={@JoinColumn(name="post_id")},
               inverseJoinColumns={@JoinColumn(name="user_id")})
	private Set<User> likes = new HashSet<User>();

	@ManyToMany
	@Cascade(value=CascadeType.ALL)
	@JoinTable(name="tags",
			joinColumns= {@JoinColumn(name="post_id")},
			inverseJoinColumns= {@JoinColumn(name="user_id")})
	private Set<User> tags = new HashSet<User>();

	@OneToMany(mappedBy="post")
	@Cascade(value=CascadeType.ALL)
	private Set<Comment> comments = new HashSet<Comment>();
//	private List<Hashtag> hashtags;

	public Post() {}
	
	public Post(User user,Media media, Calendar postDate, String content) {
		this.user = user;
//		this.media = media;
		this.postDate = postDate;
		this.content = content;
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

	public Set<User> getLikes() {
		return likes;
	}

	public void setLikes(Set<User> likes) {
		this.likes = likes;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

//	public Set<Hashtag> getHashtags() {
//		return hashtags;
//	}
//
//	public void setHashtags(Set<Hashtag> hashtags) {
//		this.hashtags = hashtags;
//	}

	public Set<User> getTags() {
		return tags;
	}

	public void setTags(Set<User> tags) {
		this.tags = tags;
	}

//	public Media getMedia() {
//		return media;
//	}
//
//	public void setMedia(Media media) {
//		this.media = media;
//	}

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
	
}
