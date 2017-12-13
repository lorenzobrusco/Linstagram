package it.unical.linstagram.tests;

import static org.junit.Assert.assertNotNull;

import org.hibernate.Session;
import org.junit.Test;

import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.HibernateUtil;
import it.unical.linstagram.persistence.ModelDAO;
import it.unical.linstagram.persistence.UserDAO;

public class UserTest {

	@Test
	public void followerTest() {
		// User u=new User("Eliana", "elianalovehitler@gmail.com", "Leader");
		// User u1=new User("A","A","A");
		// User u2=new User("B","B","B");
		//
		// ModelDAO.getInstance().save(u);
		//
		// u.getFollowings().add(u1);
		// u.getFollowings().add(u2);
		//
		// ModelDAO.getInstance().update(u);

		// ModelDAO.getInstance().update(u1);
		// ModelDAO.getInstance().update(u2);

		Session session = HibernateUtil.getHibernateSession();
		User res = (User) session.createQuery("FROM  User u where u.username=:username").setParameter("username", "A")
				.uniqueResult();

		for (User user : res.getFollowers()) {
			System.out.println(user.getUsername());
		}

		User res1 = (User) session.createQuery("FROM  User u where u.username=:username")
				.setParameter("username", "Eliana").uniqueResult();

		for (User user : res1.getFollowings()) {
			System.out.println(user.getUsername());
		}
		session.close();

	}

	@Test
	public void signinTest() {
		User user = UserDAO.getInstance().getUserByUsernameAndPass("Eliana", "X");
		assertNotNull(user);
	}

	@Test
	public void signupTest() {
		User user = new User("Paolo", "GiuseppeFigo92@libero.it", "aba");
		ModelDAO.getInstance().save(user);
		User userByUsername = UserDAO.getInstance().getUserByUsername("Paolo");
		assertNotNull(userByUsername);
	}

}
