package it.unical.linstagram.domain;

public class PublicProfile extends PrivateProfile {

	
	public PublicProfile(User user) {
		super(user);
		this.tagged = user.getTagged();
		this.posts = user.getPosts();
	}
}
