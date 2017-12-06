package it.unical.linstagram.persistence;

import java.util.List;

import it.unical.linstagram.domain.User;

public interface IUserDAO {

	void save(User user);
	
	User fingUserById(int id);
	
	List<User> allUsers();
	
	
}
