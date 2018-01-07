package it.unical.linstagram.persistence;

import java.util.List;
import java.util.Set;

import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;

public interface IUserDAO {
	
	List<User> getAllUser();
	
	User getUserByUsername(String username);
	User getUserByUsernameAndPass(String username, String password);
	User getUserByEmail(String email);
	User getUserByEmailAndPass(String email, String password);
	User getUserByUsernameAndEmail(String username, String email);
	
	String getPasswordByUsername(String username);
	String getPasswordByEmail(String email);
	
	List<Post> getPostByUsername(String username);
	List<Post> getBookmarksByUsername(String username);
	List<Post> getTaggedPostByUsername(String username);
	
	List<User> getFollowingByUsername(String username);
	List<User> getFollowerByUsername(String username);
	
	int searchRequestFollow(String usernameFrom, String usernameTo);
	boolean existRequestFollow(String usernameFrom, String usernameTo);
					
	User inizializeLists(String username);
	void inizializeListUser(Set<?> set);
	
	List<User> getSuggestions(String queryString);
	List<User> getSuggestionsName(String string);	
}