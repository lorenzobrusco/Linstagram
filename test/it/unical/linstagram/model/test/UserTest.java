package it.unical.linstagram.model.test;

import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.HibernateUtil;
import it.unical.linstagram.persistence.ModelDAO;
import it.unical.linstagram.persistence.UserDAO;

public class UserTest extends AbstractModelTest {
 
	@Test
	public void followerTest() {
		 User u=new User("Eliana", "elianalovehitler@gmail.com", "Leader");
		 User u1=new User("A","A","A");
		 User u2=new User("B","B","B");
		
		 new ModelDAO().save(u);
		
		 u.getFollowings().add(u1);
		 u.getFollowings().add(u2);
		
		 new ModelDAO().update(u);

		 new ModelDAO().update(u1);
		 new ModelDAO().update(u2);

		Session session = HibernateUtil.getSession();
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
		User user = new UserDAO().getUserByUsernameAndPass("Paolo", "aba");
		Assert.assertNull(user);
	}

	@Test
	public void signupTest() {
		User user = new User("Paolo", "GiuseppeFigo92@libero.it", "aba");
		new ModelDAO().save(user);
		User userByUsername = new UserDAO().getUserByUsername("Paolo");
		Assert.assertNotNull(userByUsername);
	}

}
