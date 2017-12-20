package it.unical.linstagram.model.test;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.unical.linstagram.model.Media;
import it.unical.linstagram.model.Media.Media_Type;
import it.unical.linstagram.model.Story;
import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.HibernateUtil;
import it.unical.linstagram.persistence.IStoryDAO;
import it.unical.linstagram.persistence.ModelDAO;
import it.unical.linstagram.persistence.StoryDAO;

public class TestStory {

//	@Autowired
//	ModelDAO modelDAO;
//	
//	@Autowired
//	StoryDAO storyDAO;
//	
	@BeforeClass
	public static void init() {
		HibernateUtil.CreateSessionFactory(true);
	}
	
	@Before
	public void cleaningDB() {
		Session session = HibernateUtil.getHibernateSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.createQuery("delete from Story").executeUpdate();
			session.createQuery("delete from User").executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
	}
	
	@Test
	public void addStory() {
		User eliana = new User("Eliana","email","pass");
		
		Media media = new Media(Media_Type.IMAGE,"urlmedia");
		
		Story story = new Story(eliana, media);
		
		ModelDAO modelDAO = new ModelDAO();
		modelDAO.save(eliana);
		modelDAO.save(story);
		
		IStoryDAO storyDAO = new StoryDAO();

		List<Story> stories = storyDAO.getStoriesByUsername("Eliana");
		Assert.assertEquals(1,stories.size());
	}
	
	@Test
	public void addStoryViewer() {
		User eliana = new User("Eliana","email","pass");
		User manuel = new User("Manuel","e","pass");
		User paola = new User("Paola","paola","pass");
		
		Media media = new Media(Media_Type.IMAGE,"urlmedia");
		
		Story story = new Story(eliana, media);
		
		story.getViewers().add(manuel);
		story.getViewers().add(paola);
		
		ModelDAO modelDAO = new ModelDAO();
		
		modelDAO.save(eliana);
		modelDAO.save(manuel);
		modelDAO.save(paola);
		
		modelDAO.save(story);
		
		IStoryDAO storyDAO = new StoryDAO();
		List<User> viewers = storyDAO.getViewersOfStory(story.getId());
		Assert.assertEquals(2, viewers.size());
		
	}
	
}
