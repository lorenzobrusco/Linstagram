package it.unical.linstagram.model;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.search.annotations.*;

@Entity
@Table(name="user")
@Indexed
public class User{

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name="username", nullable = false, unique = true)
	@Fields({
		@Field(name = "username", index = Index.YES, store = Store.YES),
	})
	private String username;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(name="name")
	@Fields({
		@Field(name = "name", index = Index.YES, store = Store.YES),
	})
	private String name;

	@Column (name="surname")
	@Fields({
		@Field(name = "surname", index = Index.YES, store = Store.YES),
	})
	private String surname;

	@Column
	private Calendar birthdate;

	@Column
	private Gender gender;

	@Column
	private String biography;

	@Column
	private boolean privateProfile = false; //default the profile is public

	@Column
	private String photoProfile = "images/default.png"; //default image profile


	@ManyToMany
	@Cascade(value= CascadeType.ALL)
	@JoinTable(name="following",
	joinColumns={@JoinColumn(name="followed")},
	inverseJoinColumns={@JoinColumn(name="following")})
	private Set<User> followings = new HashSet<User>();


	@ManyToMany(mappedBy="followings")
	private Set<User> followers = new HashSet<User>();

	@OneToMany(mappedBy = "user", orphanRemoval=true)
	@Cascade(value=CascadeType.ALL)
	private Set<Post> posts = new HashSet<Post>();

	@ManyToMany(mappedBy = "tags")
	private Set<Post> tagged = new HashSet<Post>();

	@ManyToMany
	@Cascade(value=CascadeType.ALL)
	@JoinTable(name="bookmark",
	joinColumns= {@JoinColumn(name="user_id")},
	inverseJoinColumns= {@JoinColumn(name="post_id")})
	private Set<Post> bookmarks = new HashSet<Post>();

	public User() {}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	public User(String username, String email, String password, String name, String surname) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Calendar getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Calendar birthdate) {
		this.birthdate = birthdate;
	}

	public boolean isPrivateProfile() {
		return privateProfile;
	}

	public void setPrivateProfile(boolean privateProfile) {
		this.privateProfile = privateProfile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public Set<User> getFollowings() {
		return followings;
	}

	public void setFollowings(Set<User> followings) {
		this.followings = followings;
	}

	public Set<User> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<User> followers) {
		this.followers = followers;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public Set<Post> getTagged() {
		return tagged;
	}

	public void setTagged(Set<Post> tagged) {
		this.tagged = tagged;
	}

	public Set<Post> getBookmarks() {
		return bookmarks;
	}
	public void setBookmarks(Set<Post> bookmarks) {
		this.bookmarks = bookmarks;
	}
	public String getPhotoProfile() {
		return photoProfile;
	}
	public void setPhotoProfile(String photoProfile) {
		this.photoProfile = photoProfile;
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
