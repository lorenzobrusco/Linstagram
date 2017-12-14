package it.unical.linstagram.model.test;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.HibernateUtil;
import it.unical.linstagram.persistence.ModelDAO;

public class TestPost {

	@Test
	public void testLikes() {
		
		User eliana = new User("Eliana","email","pass");
		User manuel = new User("Manuel","e","pass");
		
		Post post = new Post(eliana,null,Calendar.getInstance(),"Sono scema");
		
		
		post.getLikes().add(manuel);
		
		final Session session = HibernateUtil.getHibernateTestSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
//			session.save(manuel);
//			session.save(eliana);
			session.save(post);
			transaction.commit();

		} catch (Exception e) {
			transaction.rollback();
		}
		

		List<User> likes =  session.createQuery("SELECT post.likes FROM Post post WHERE post.id =:idPost")
				.setParameter("idPost",post.getId()).list();
		
		Assert.assertEquals("Manuel",likes.get(0).getUsername());
		session.close();
	}
}
