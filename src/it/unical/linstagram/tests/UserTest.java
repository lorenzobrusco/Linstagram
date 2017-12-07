package it.unical.linstagram.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import it.unical.linstagram.domain.User;
import it.unical.linstagram.persistence.UserDAO;

public class UserTest {

	@Test
	public void addUser() {

		UserDAO userDAO = UserDAO.getInstance();

		User user = new User();
		user.setEmail("losllo@hotmail.it");
		user.setUsername("lsollo");
		user.setPassword("psw");
		userDAO.save(user);
		List<User> users = userDAO.allUsers();
		for (User usertmp : users) {
			System.out.println(usertmp.getUsername());
		}
		assertEquals(2, users.size());
	}

	@Test
	public void findUser() {
		UserDAO userDAO = UserDAO.getInstance();
		User user = new User();
		user.setEmail("lollo@hotmail.it");
		user.setUsername("lollolollo");
		user.setPassword("psw");
		userDAO.save(user);
		User u = userDAO.fingUserById("lollolollo");
		System.out.println("[FIND] " + u.getUsername());
	}
}
