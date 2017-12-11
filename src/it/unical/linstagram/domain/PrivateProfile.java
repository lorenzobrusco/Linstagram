package it.unical.linstagram.domain;

public class PrivateProfile extends Profile{
	
	public PrivateProfile(User user) {
		this.surname = user.getSurname();
		this.name = user.getName();
		this.biography = user.getBiography();
		this.numberOfFollowers = user.getFollowers().size();
		this.numberOfFollowing =  user.getFollowing().size();
		this.numberOfPosts = user.getPosts().size();
		this.photoProfile = user.getPhotoProfile();
		this.tagged = null;
		this.posts = null;
		this.bookmarks = null;
		

	}
}
