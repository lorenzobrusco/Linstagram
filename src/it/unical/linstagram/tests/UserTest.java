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
		
		ModelDAO.getInstance().save(u1);
		ModelDAO.getInstance().save(u2);
		
		u.getFollowings().add(u1);
		u.getFollowings().add(u2);
		
		System.out.println("_____________________________________________________________________");
		System.out.println(u.getUsername());
		System.out.println("_____________________________________________________________________");
		
//		ModelDAO.getInstance().save(u);
		ModelDAO.getInstance().update(u);
//		ModelDAO.getInstance().update(u1);
//		ModelDAO.getInstance().update(u2);
		
	}

}
