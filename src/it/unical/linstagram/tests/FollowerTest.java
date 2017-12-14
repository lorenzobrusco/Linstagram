package it.unical.linstagram.tests;

import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.HibernateUtil;
import it.unical.linstagram.persistence.ModelDAO;

public class FollowerTest {
	
	@Test
	void followerTest() {
		User eliana=new User("Eliana", "elianalovehitler@gmail.com", "Leader");
		User manuel=new User("Manuel","A","A");
		User ciccio=new User("Ciccio","B","B");
		User alessio=new User("Alessio","B","B");
		User lorenzo=new User("Lorenzo","B","B");
		User paola=new User("Paola","B","B");
		
		ModelDAO.getInstance().save(manuel);
		ModelDAO.getInstance().save(alessio);
		ModelDAO.getInstance().save(ciccio);
		ModelDAO.getInstance().save(eliana);
		
		
		eliana.getFollowings().add(manuel);
		eliana.getFollowings().add(paola);
	
		ciccio.getFollowings().add(alessio);
		
		alessio.getFollowings().add(lorenzo);
		
		manuel.getFollowings().add(eliana);
		manuel.getFollowings().add(paola);
		manuel.getFollowings().add(ciccio);
		manuel.getFollowings().add(lorenzo);
		manuel.getFollowings().add(alessio);
		
		ModelDAO.getInstance().update(eliana);
		ModelDAO.getInstance().update(manuel);
		ModelDAO.getInstance().update(ciccio);
		ModelDAO.getInstance().update(alessio);
		
		
		Session sex = HibernateUtil.getHibernateSession();
		Collection<User> users= sex.createQuery("SELECT user.following FROM User user where user.id=:u1id").setParameter("u1id", eliana.getId()).list();
		System.out.println(users.size());

		for (User user : users) {
			System.out.println(user.getUsername());
		}
		sex.close();
	}
}
