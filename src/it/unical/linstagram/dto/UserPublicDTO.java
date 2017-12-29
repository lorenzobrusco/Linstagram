package it.unical.linstagram.dto;

import java.util.Set;

import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;

public class UserPublicDTO extends UserPrivateDTO {

	private String biography;
	
	private Set<User> followings;
	private Set<User> followers;
	private Set<Post> posts;
	private Set<Post> tagged;
	
//	private boolean followed; //se questo utente è seguito da un'altro utente(della sessione)
	
	
	public UserPublicDTO(User user, boolean followed) {
		super(user, followed);
		this.biography = user.getBiography();
		
		this.followings = user.getFollowings();
		this.followers = user.getFollowers();
		this.posts = user.getPosts();
		this.tagged = user.getTagged();
		
//		this.followed = followed;
	}

	public String getBiography() {
		return biography;
	}

	public Set<User> getFollowings() {
		return followings;
	}

	public Set<User> getFollowers() {
		return followers;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public Set<Post> getTagged() {
		return tagged;
	}
	
//	public boolean isFollowed() {
//		return followed;
//	}
//	
}
