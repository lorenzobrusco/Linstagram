package it.unical.linstagram.persistence;

import java.util.List;

import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;

public interface IUserDAO {
	
	User getUserByUsername(String username);
	User getUserByUsernameAndPass(String username, String password);
	User getUserByEmail(String email);
	User getUserEmailAndPass(String email, String password);
	
	String getPasswordByUsername(String username);
	String getPasswordByEmail(String email);
	
	List<Post> getPostByUsername(String username);
	List<Post> getBookmarksByUsername(String username);
	List<Post> getTaggedPostByUsername(String username);
	
	List<User> getSuggestions(String queryString);
	List<User> getSuggestionsName(String string);	
}