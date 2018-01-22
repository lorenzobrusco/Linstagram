package it.unical.linstagram.dto;

import java.util.Set;

import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;

public class UserPublicDTO extends UserPrivateDTO {

	private String biography;
	
	private int followings;
	private int followers;
	private Set<Post> posts;
	private Set<Post> tagged;
	
//	private boolean followed; //se questo utente è seguito da un'altro utente(della sessione)
	
	
	public UserPublicDTO(User user, boolean followed, boolean request_send, boolean request_received, int numberOfFollowings
			, int numberOfFollowers) {
		super(user, followed, request_send, request_received);
		this.biography = user.getBiography();
		
		this.setFollowings(numberOfFollowings);
		this.setFollowers(numberOfFollowers);
		
		this.posts = user.getPosts();
		this.tagged = user.getTagged();
	}

	public String getBiography() {
		return biography;
	}


	public Set<Post> getPosts() {
		return posts;
	}

	public Set<Post> getTagged() {
		return tagged;
	}

	public int getFollowers() {
		return followers;
	}

	public void setFollowers(int followers) {
		this.followers = followers;
	}

	public int getFollowings() {
		return followings;
	}

	public void setFollowings(int followings) {
		this.followings = followings;
	}

	
}
