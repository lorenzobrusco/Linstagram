package it.unical.linstagram.tests;

import org.junit.Test;

import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.ModelDAO;

public class UserTest {
	
	@Test
	public void followerTest() {
		User u=new User("Eliana", "elianalovehitler@gmail.com", "Leader");
		User u1=new User("A","A","A");
		User u2=new User("B","B","B");
		
		ModelDAO.getInstance().save(u);
		ModelDAO.getInstance().save(u1);
		ModelDAO.getInstance().save(u2);
		
		u.getFollowings().add(u1);
		u.getFollowings().add(u2);
		
		ModelDAO.getInstance().update(u);
		
	}

}
