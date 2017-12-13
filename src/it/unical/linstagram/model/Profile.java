package it.unical.linstagram.domain;

import java.util.List;

public abstract class Profile {

	protected String username;
	protected String name;
	protected String surname;
	protected int numberOfFollowers;
	protected int numberOfFollowing;
	protected int numberOfPosts;
	protected String biography;
	protected List<Post> posts;
	protected List<Post> tagged;
	protected Media photoProfile;
	protected List<Post> bookmarks;
	
	
	public String getUsername() {
		return username;
	}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public int getNumberOfFollowers() {
		return numberOfFollowers;
	}
	public int getNumberOfFollowing() {
		return numberOfFollowing;
	}
	public int getNumberOfPosts() {
		return numberOfPosts;
	}
	public String getBiography() {
		return biography;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public List<Post> getTagged() {
		return tagged;
	}
	public Media getPhotoProfile() {
		return photoProfile;
	}
	public List<Post> getBookmarks() {
		return bookmarks;
	}
	
}
