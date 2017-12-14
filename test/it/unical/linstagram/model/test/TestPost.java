package it.unical.linstagram.model.test;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.HibernateUtil;
import it.unical.linstagram.persistence.ModelDAO;

public class TestPost {

//	@Test
	public void testPost() {
		User eliana = new User("Eliana","email","pass");
		
		Post post = new Post(eliana,null,Calendar.getInstance(),"Sono scema");
		Post post1 = new Post(eliana,null,Calendar.getInstance(),"Sono Stupida");
		Post post3 = new Post(eliana,null,Calendar.getInstance(),"Sono brava");
		
		final Session session = HibernateUtil.getHibernateTestSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
//			session.save(manuel);
//			session.save(eliana);
			session.save(post);
			session.save(post3);
			session.save(post1);
//			session.save(eliana);
			transaction.commit();

		} catch (Exception e) {
			transaction.rollback();
		}

//		Insieme non aggiornato, è giusto???????????????????????????????????
//		Set<Post> posts = eliana.getPosts();

		List<Post> posts =  session.createQuery("SELECT user.posts FROM User user WHERE user.id =:idUser")
				.setParameter("idUser",eliana.getId()).list();
		for(Post p : posts) {
			System.out.println(p.getContent());
		}
		Assert.assertEquals(3,posts.size());
		session.close();
		
		
	}
//	@Test
	public void testLikes() {
		
		User eliana = new User("Eliana","email","pass");
		User manuel = new User("Manuel","e","pass");
		User paola = new User("Paola","paola","pass");
		
		Post post = new Post(eliana,null,Calendar.getInstance(),"Sono scema");
		
		
		post.getLikes().add(manuel);
		post.getLikes().add(paola);
		
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
		for(User user : likes) {
			System.out.println(user.getUsername());
		}
		Assert.assertEquals(2,likes.size());
		session.close();
	}
	
//	@Test
	public void testTag() {
		
		User eliana = new User("Eliana","email","pass");
		User manuel = new User("Manuel","e","pass");
		User paola = new User("Paola","paola","pass");
		
		Post post = new Post(eliana,null,Calendar.getInstance(),"Sono scema");
		
		
		post.getTags().add(manuel);
		post.getTags().add(paola);
		
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
		
		
		List<User> tags =  session.createQuery("SELECT post.tags FROM Post post WHERE post.id =:idPost")
				.setParameter("idPost",post.getId()).list();
		for(User user : tags) {
			System.out.println(user.getUsername());
		}
		Assert.assertEquals(2,tags.size());
		session.close();
	}
//	@Test
	public void testUserTagged() {
		
		User eliana = new User("Eliana","email","pass");
		User manuel = new User("Manuel","e","pass");
		User paola = new User("Paola","paola","pass");
		
		Post post = new Post(eliana,null,Calendar.getInstance(),"Sono scema");
		
		
		post.getTags().add(manuel);
		post.getTags().add(paola);
		
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
//		System.out.println(manuel.getId());
//	
//		Set<Post> posts = manuel.getTagged();
//		String name =  session.createQuery("SELECT user.name FROM User user WHERE user.id =:idUser",String.class).setParameter("idUser",manuel.getId()).uniqueResult();
//		manuel =  session.createQuery("SELECT user FROM User user WHERE user.id =:idUser",User.class).setParameter("idUser",manuel.getId()).uniqueResult();
		List<Post> tagged =  session.createQuery("SELECT user.tagged FROM User user WHERE user.id =:idUser").setParameter("idUser",manuel.getId()).list();
		
		for(Post p : tagged) {
			System.out.println(p.getContent());
		}
		Assert.assertEquals(1,tagged.size());
		
		
//		for(Post p : manuel.getTagged()) {
//			System.out.println(p.getContent());
//		}
//		Assert.assertEquals(1,manuel.getTagged().size());
//		session.close();
	}
	
	@Test
	public void testUserBookmarks() {
		
		User eliana = new User("Eliana","email","pass");
		User manuel = new User("Manuel","e","pass");
		User paola = new User("Paola","paola","pass");
		
		Post post = new Post(eliana,null,Calendar.getInstance(),"Sono Cioa");
		Post post1 = new Post(paola,null,Calendar.getInstance(),"Sono ceom");
		Post post2 = new Post(eliana,null,Calendar.getInstance(),"Sono sceasfafma");
		Post post3 = new Post(eliana,null,Calendar.getInstance(),"Sono scemffffffffffffa");
		
		
		post.getTags().add(manuel);
		post.getTags().add(paola);
		
		manuel.getBookmarks().add(post);
		manuel.getBookmarks().add(post3);
		manuel.getBookmarks().add(post2);
		manuel.getBookmarks().add(post1);
		
		final Session session = HibernateUtil.getHibernateTestSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(manuel);
			transaction.commit();
			
		} catch (Exception e) {
			transaction.rollback();
		}
		
		List<Post> bookmarks =  session.createQuery("SELECT user.bookmarks FROM User user WHERE user.id =:idUser").setParameter("idUser",manuel.getId()).list();
		
		for(Post p : bookmarks) {
			System.out.println(p.getContent());
		}
		Assert.assertEquals(4,bookmarks.size());
		
		
//		for(Post p : manuel.getTagged()) {
//			System.out.println(p.getContent());
//		}
//		Assert.assertEquals(1,manuel.getTagged().size());
//		session.close();
	}
	
	
}
