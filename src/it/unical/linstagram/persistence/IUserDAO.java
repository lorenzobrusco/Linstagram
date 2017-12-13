package it.unical.linstagram.persistence;

import it.unical.linstagram.model.User;

public interface IUserDAO {
	
	User getUserByUsername(String username);
	User getUserByUsernameAndPass(String username, String password);
	User getUserByEmail(String email);

}
