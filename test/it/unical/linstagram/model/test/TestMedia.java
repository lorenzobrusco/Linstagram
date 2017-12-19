package it.unical.linstagram.model.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

import it.unical.linstagram.model.Media;
import it.unical.linstagram.model.Media.Media_Type;
import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.HibernateUtil;
import it.unical.linstagram.persistence.ModelDAO;

public class TestMedia {
	
	
	@Test
	public void addMedia() {
	
//		HibernateUtil.CreateSessionFactory(true);
		User eliana = new User("Eliana","email","pass");
		ModelDAO md= new ModelDAO();
	
		List<Media> media = new ArrayList<>();
		media.add(new Media(Media_Type.IMAGE,"url1"));
		media.add(new Media(Media_Type.IMAGE,"url2"));
		Post post = new Post(eliana,media,Calendar.getInstance(),"Sono scema");
		
		md.save(post);
		
		final Session session = HibernateUtil.getHibernateSession();
	
		Post p =  session.createQuery("SELECT post FROM Post post WHERE post.id =:idPost",Post.class)
				.setParameter("idPost",post.getId()).uniqueResult();
		
		for(Media m : p.getMedia()) {
			System.out.println(m.getUrl());
		}
		
		Assert.assertEquals(2,p.getMedia().size());
		session.close();

		
	}
}
