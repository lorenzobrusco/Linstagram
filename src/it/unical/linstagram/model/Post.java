package it.unical.linstagram.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OrderBy;

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
	
	@ElementCollection
	@CollectionTable(name="media", joinColumns=@JoinColumn(name="post"))
	private List<Media> media = new ArrayList<Media>();
	
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

//TODO : potrebbero essere caricati lazy
	@OneToMany(mappedBy="post")
	@Cascade(value=CascadeType.ALL)
	private Set<Comment> comments = new HashSet<Comment>();
	
	@ManyToMany
	@JoinTable(name="hashtag_post",
		joinColumns= {@JoinColumn(name="id_post")},
		inverseJoinColumns= {@JoinColumn(name="id_hashtag")})
	@Cascade(value=CascadeType.ALL)
	private List<Hashtag> hashtags = new ArrayList<Hashtag>();

	public Post() {}
	
	public Post(User user,List<Media> media, Calendar postDate, String content) {
		this.user = user;
		this.postDate = postDate;
		this.content = content;
		this.media = media;
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

	public List<Hashtag> getHashtags() {
		return hashtags;
	}

	public void setHashtags(List<Hashtag> hashtags) {
		this.hashtags = hashtags;
	}

	public Set<User> getTags() {
		return tags;
	}

	public void setTags(Set<User> tags) {
		this.tags = tags;
	}

	public List<Media> getMedia() {
		return media;
	}

	public void setMedia(List<Media> media) {
		this.media = media;
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
		Post other = (Post) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
