package it.unical.linstagram.model.test;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

import it.unical.linstagram.model.Comment;
import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.HibernateUtil;

public class TestComment {

	@Test
	public void testComment() {
		User eliana = new User("Eliana","email","pass");
		User manuel = new User("Manuel","memail","pass");

		Post post = new Post(eliana,null,Calendar.getInstance(),"Sono scema");

		post.getComments().add(new Comment("Si è vero",manuel,post,Calendar.getInstance()));
		post.getComments().add(new Comment("Che stupido",eliana,post,Calendar.getInstance()));
		
		final Session session = HibernateUtil.getHibernateTestSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(post);
			transaction.commit();

		} catch (Exception e) {
			transaction.rollback();
		}

		List<Comment> comments =  session.createQuery("SELECT post.comments FROM Post post WHERE post.id =:idPost")
				.setParameter("idPost",post.getId()).list();
		for(Comment c : comments) {
			System.out.println(c.getUser().getUsername()+" ha commentato : "+c.getContent());
		}
		Assert.assertEquals(2,comments.size());
		session.close();

	}
}
