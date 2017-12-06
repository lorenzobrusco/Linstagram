package it.unical.linstagram.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import it.unical.linstagram.domain.User;
import it.unical.linstagram.persistence.UserDao;


public class UserTest {

	
	@Test
	public void addUser() {
		UserDao userDAO = new UserDao();
		User user = new User();
		user.setEmail("lollo@hotmail.it");
		user.setUsername("lollo");
		user.setPassword("psw");
		userDAO.save(user);
		List<User> users = userDAO.allUsers();
		for(User usertmp : users) {
			System.out.println(usertmp.getUsername());
		}
		assertEquals(1, users.size());
	}
}
