package it.unical.linstagram.domain;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class User{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(nullable = false, unique = true)
	private String username;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	private String name;
	private String surname;
	// FIXME Forse va usato calendar (Parola di Alviano)
	private Calendar birthdate;
	private Gender gender;
	private String biography;
	private boolean privateProfile;
	private Media photoProfile;
	private List<User> followers; // chi ti segue
	private List<User> following; // chi segui
	private List<Post> posts;
	private List<Post> tagged;
	private List<Post> bookmarks;
	
	public User() {}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
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

	public Media getPhotoProfile() {
		return photoProfile;
	}

	public void setPhotoProfile(Media photoProfile) {
		this.photoProfile = photoProfile;
	}

	public List<User> getFollowers() {
		return followers;
	}

	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}

	public List<User> getFollowing() {
		return following;
	}

	public void setFollowing(List<User> following) {
		this.following = following;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Post> getTagged() {
		return tagged;
	}

	public void setTagged(List<Post> tagged) {
		this.tagged = tagged;
	}

	public List<Post> getBookmarks() {
		return bookmarks;
	}

	public void setBookmarks(List<Post> bookmarks) {
		this.bookmarks = bookmarks;
	}
	
	

}
