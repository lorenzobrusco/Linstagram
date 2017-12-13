package it.unical.linstagram.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.HibernateUtil;
import it.unical.linstagram.persistence.ModelDAO;

class UserTest {

	@Test
	void SaveTest() {
		User u=new User("Eliana", "elianalovehitler@gmail.com", "Leader");
		System.out.println(ModelDAO.getInstance().save(u));
		assertEquals(1, ModelDAO.getInstance().getAll(User.class).size());
	}
	
	@Test
	void followerTest() {
		User u=new User("Eliana", "elianalovehitler@gmail.com", "Leader");
		User u1=new User("A","A","A");
		User u2=new User("B","B","B");
		
		ModelDAO.getInstance().save(u);
		ModelDAO.getInstance().save(u1);
		ModelDAO.getInstance().save(u2);
		
		u.getFollowers().add(u1);
		u.getFollowers().add(u2);
		
		ModelDAO.getInstance().update(u);
		
		Session sex = HibernateUtil.getHibernateSession();
		List<User> list = sex.createNativeQuery("SELECT * FROM follower",User.class).list();
		System.out.println("_____________________________________________________________________");
		for (User user : list) {
			System.out.println(user.getName());
		}
				
		
		System.out.println("_____________________________________________________________________");
		
	}

}
