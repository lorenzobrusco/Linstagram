package it.unical.linstagram.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import it.unical.linstagram.persistence.PostDAO;
import it.unical.linstagram.persistence.UserDAO;

public class TestPostDAO {

//	@Test
	public void testBookmarksUser() {
		
		User eliana = new User("Eliana","email","pass");
		User manuel = new User("Manuel","e","pass");
		User paola = new User("Paola","paola","pass");
		
		Post post = new Post(eliana,null,Calendar.getInstance(),"Sono Cioa");
		Post post1 = new Post(paola,null,Calendar.getInstance(),"Pararararra");
		Post post2 = new Post(eliana,null,Calendar.getInstance(),"Sono sceasfafma");
		
		ModelDAO.getInstance().save(post1);
		
		eliana.getPosts().add(post);
		eliana.getPosts().add(post2);
//		eliana.getPosts().add(post3);
		
		manuel.getBookmarks().add(post1);
		ModelDAO.getInstance().save(eliana);
		ModelDAO.getInstance().save(manuel);
		
		manuel.getBookmarks().add(post2);
		ModelDAO.getInstance().update(manuel);
		
		Post post3 = new Post(eliana,null,Calendar.getInstance(),"Sono scemffffffffffffa");

		ModelDAO.getInstance().save(post3); // Simulazione dell'aggiunta di un post da parte dell'utente 
											// (è inutile fare una query per inserire)
		
		UserDAO dao = UserDAO.getInstance();
		
//		List<Post> posts = dao.getPostByUsername("eliana");
		List<Post> posts = dao.getBookmarksByUsername("manuel");
		
		for(Post p : posts) {
			System.out.println(p.getContent());
		}
		
		assertEquals(1, posts.size());
	}

	
//	@Test
	public void testUserTagged() {
		
		User eliana = new User("Eliana","email","pass");
		User manuel = new User("Manuel","e","pass");
		User paola = new User("Paola","paola","pass");
		
		Post post = new Post(eliana,null,Calendar.getInstance(),"Sono scema");
		
		post.getTags().add(manuel);
		
		ModelDAO.getInstance().save(post);
		
		post.getTags().add(paola);
		ModelDAO.getInstance().update(post);
		
		UserDAO dao = UserDAO.getInstance();
		List<Post> tagged =  dao.getTaggedPostByUsername("manuel");
		
		for(Post p : tagged) {
			System.out.println(p.getContent());
		}
		Assert.assertEquals(1,tagged.size());
	}
	
	
//	@Test
	public void testLikes() {
		
		User eliana = new User("Eliana","email","pass");
		User manuel = new User("Manuel","e","pass");
		User paola = new User("Paola","paola","pass");
		
		Post post = new Post(eliana,null,Calendar.getInstance(),"Sono scema");
		
		post.getLikes().add(manuel);
		ModelDAO.getInstance().save(post);
		
		post.getLikes().add(paola);
		ModelDAO.getInstance().update(post);
		
		PostDAO dao = PostDAO.getInstance();

		List<User> likes =  dao.getLikesByPostId(post.getId());
		for(User user : likes) {
			System.out.println(user.getUsername());
		}
		Assert.assertEquals(2,likes.size());
		
	}
		
	@Test
	public void testTag() {
		
		User eliana = new User("Eliana","email","pass");
		User manuel = new User("Manuel","e","pass");
		User paola = new User("Paola","paola","pass");
		
		Post post = new Post(eliana,null,Calendar.getInstance(),"Sono scema");
		
		post.getTags().add(manuel);
		ModelDAO.getInstance().save(post);
		
		post.getTags().add(paola);
		ModelDAO.getInstance().update(post);
		
		PostDAO dao = PostDAO.getInstance();
		
		List<User> tags = dao.getUserTaggedByPostId(post.getId());
		for(User user : tags) {
			System.out.println(user.getUsername());
		}
		Assert.assertEquals(2,tags.size());
		
	}
	
	
}
