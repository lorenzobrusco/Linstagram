package it.unical.linstagram.persistence;

import it.unical.linstagram.domain.User;

public interface IUserDAO {
	
	/**Default DAO Operation**/
	public void save(User user);
	public User findById(int id);
	boolean update(User user);
	boolean delete(User user);
	
	boolean addFollowing(User requestUser,User user);
}
