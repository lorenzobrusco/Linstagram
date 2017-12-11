package it.unical.linstagram.domain;

public class MyProfile extends PublicProfile{

	
	public MyProfile(User user) {
		super(user);
		this.bookmarks = user.getBookmarks();
	}
}
