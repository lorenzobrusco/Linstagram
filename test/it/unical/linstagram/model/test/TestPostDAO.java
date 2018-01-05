package it.unical.linstagram.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import it.unical.linstagram.model.Comment;
import it.unical.linstagram.model.Hashtag;
import it.unical.linstagram.model.Media;
import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.HibernateUtil;
import it.unical.linstagram.persistence.ModelDAO;
import it.unical.linstagram.persistence.PostDAO;
import it.unical.linstagram.persistence.UserDAO;

public class TestPostDAO extends AbstractModelTest{
	
	private static ModelDAO md;
	private static PostDAO pd;
	@BeforeClass
	public static void init() {
		md = new ModelDAO();
		pd = new PostDAO();
	}

//	@Test
	public void testBookmarksUser() {
		
		
		User eliana = new User("Eliana","email","pass");
		User manuel = new User("Manuel","e","pass");
		User paola = new User("Paola","paola","pass");
		
		Post post = new Post(eliana,null,Calendar.getInstance(),"Sono Cioa");
		Post post1 = new Post(paola,null,Calendar.getInstance(),"Pararararra");
		Post post2 = new Post(eliana,null,Calendar.getInstance(),"Sono sceasfafma");
		
		md.save(post1);
		
		eliana.getPosts().add(post);
		eliana.getPosts().add(post2);
//		eliana.getPosts().add(post3);
		
		manuel.getBookmarks().add(post1);
		md.save(eliana);
		md.save(manuel);
		
		manuel.getBookmarks().add(post2);
		md.update(manuel);
		
		Post post3 = new Post(eliana,null,Calendar.getInstance(),"Sono scemffffffffffffa");

		md.save(post3); // Simulazione dell'aggiunta di un post da parte dell'utente 
											// (è inutile fare una query per inserire)
		
		UserDAO dao = new UserDAO();
		
//		List<Post> posts = dao.getPostByUsername("eliana");
		List<Post> posts = dao.getBookmarksByUsername("manuel");
		
		for(Post p : posts) {
			System.out.println(p.getContent());
		}
		
		assertEquals(1, posts.size());
	}

	
	//TODO da sistemare
//	@Test
	public void testUserTagged() {
		
		User eliana = new User("Eliana","email","pass");
		User manuel = new User("Manuel","e","pass");
		User paola = new User("Paola","paola","pass");
		
		Post post = new Post(eliana,null,Calendar.getInstance(),"Sono scema");
		
		post.getTags().add(manuel);
		
		md.save(post);
		
		post.getTags().add(paola);
		md.update(post);
		
		UserDAO dao = new UserDAO();
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
		md.save(post);
		
		post.getLikes().add(paola);
		md.update(post);
		
		PostDAO dao = new PostDAO();

		List<User> likes =  dao.getLikesByPostId(post.getId());
		for(User user : likes) {
			System.out.println(user.getUsername());
		}
		Assert.assertEquals(2,likes.size());
		
	}
		
//	@Test
	public void testTag() {
		
		User eliana = new User("Eliana","email","pass");
		User manuel = new User("Manuel","e","pass");
		User paola = new User("Paola","paola","pass");
		
		Post post = new Post(eliana,null,Calendar.getInstance(),"Sono scema");
		
		post.getTags().add(manuel);
		md.save(post);
		
		post.getTags().add(paola);
		md.update(post);
		
		PostDAO dao = new PostDAO();
		
		List<User> tags = dao.getUserTaggedByPostId(post.getId());
		for(User user : tags) {
			System.out.println(user.getUsername());
		}
		Assert.assertEquals(2,tags.size());	
	}
	
//	@Test
	public void testHashtag() {
		HibernateUtil.CreateSessionFactory(true);
		
		User eliana = new User("Eliana","email","pass");
		
		List<Media> media = new ArrayList<>();
		Post post = new Post(eliana,media,Calendar.getInstance(),"Sono scema");
		post.getHashtags().add(new Hashtag("bellofigo"));
		post.getHashtags().add(new Hashtag("cicciociccio"));
		md.save(post);
		
		PostDAO dao = new PostDAO();
		
		List<Hashtag> hashtags = dao.getHashtagByPostId(post.getId());
		
		for(Hashtag h : hashtags) {
			System.out.println(h.getHashtag());
		}
		
		Assert.assertEquals(2,hashtags.size());		
	}
	
	
//	@Test
	public void testComment() {
		HibernateUtil.CreateSessionFactory(true);
		
		User eliana = new User("Eliana","email","pass");
		
		List<Media> media = new ArrayList<>();
		Post post = new Post(eliana,media,Calendar.getInstance(),"Sono scema");
		
		Comment c1 = new Comment("bla bla", eliana, post, Calendar.getInstance());
		Comment c2 = new Comment("ciao", eliana, post, Calendar.getInstance());
		post.getComments().add(c1);
		post.getComments().add(c2);
		
		md.save(post);
		
		PostDAO dao = new PostDAO();
		
		List<Comment> comments = dao.getCommentByPostId(post.getId());
		
		for(Comment c : comments) {
			System.out.println(c.getContent());
		}
		
		Assert.assertEquals(2,comments.size());		
	}
	
	@Test
	public void testGetPostComment() {
		HibernateUtil.CreateSessionFactory(true);
		
		User eliana = new User("Eliana","email","pass");
		
		List<Media> media = new ArrayList<>();
		Post post = new Post(eliana,media,Calendar.getInstance(),"Sono scema");
		md.save(post);
		int nC = 20;
		for(int i = 0 ; i < nC; i++) {
			Comment c = new Comment("bla bla", eliana, post, Calendar.getInstance());
			post.getComments().add(c);
			
		}
		
		md.update(post);
		List<Comment> comments = pd.getCommentByPostId(post.getId(),0);
		
		for(Comment c : comments) {
			System.out.println(c.getContent());
		}
		
		Assert.assertEquals(nC,comments.size());	
	}
	
}
