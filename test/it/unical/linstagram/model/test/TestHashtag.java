package it.unical.linstagram.model.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

import it.unical.linstagram.model.Hashtag;
import it.unical.linstagram.model.Media;
import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.HibernateUtil;
import it.unical.linstagram.persistence.ModelDAO;

public class TestHashtag {

	@Test
	public void testHashtag()
	{
		User eliana = new User("Eliana","email","pass");
		
		List<Media> media = new ArrayList<>();
		Post post = new Post(eliana,media,Calendar.getInstance(),"Sono scema");
		post.getHashtags().add(new Hashtag("bellofigo"));
		post.getHashtags().add(new Hashtag("cicciociccio"));
		ModelDAO.getInstance().save(post);
		
		final Session session = HibernateUtil.getHibernateTestSession();
	
		List<Hashtag> hashtags =  session.createQuery("SELECT post.hashtags FROM Post post WHERE post.id =:idPost")
				.setParameter("idPost",post.getId()).list();
		
		for(Hashtag h : hashtags) {
			System.out.println(h.getHashtag());
		}
		
		Assert.assertEquals(2,hashtags.size());
		session.close();
	}
}
