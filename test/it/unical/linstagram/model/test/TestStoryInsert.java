package it.unical.linstagram.model.test;

import java.util.Calendar;
import java.util.List;

import javax.persistence.TemporalType;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.unical.linstagram.model.Media;
import it.unical.linstagram.model.Story;
import it.unical.linstagram.model.User;
import it.unical.linstagram.model.Media.Media_Type;
import it.unical.linstagram.persistence.HibernateUtil;
import it.unical.linstagram.persistence.IStoryDAO;
import it.unical.linstagram.persistence.ModelDAO;
import it.unical.linstagram.persistence.StoryDAO;
import it.unical.linstagram.persistence.UserDAO;

public class TestStoryInsert {
	
	@BeforeClass
	public static void init() {
		HibernateUtil.CreateSessionFactory(false);
	}
	
//	@Test
	public void getStory() {
		
		UserDAO userDAO = new UserDAO();
		StoryDAO storyDAO = new StoryDAO();
		
		User dragmaf = userDAO.getUserByUsername("dragmaf");
		List<Story> stories = storyDAO.getFollowedUsersStoriesByUsername("dragmaf");
		
		for (Story story : stories) {
			System.out.println(story.isAViewer(dragmaf));
		}
		
		Assert.assertEquals(6, stories.size());

	}
//	@Test
	public void testDifferenceDate() {
		
		Session session = HibernateUtil.getHibernateSession();
		Calendar now = Calendar.getInstance();
		Calendar yestarday = Calendar.getInstance();
		yestarday.setTimeInMillis(now.getTimeInMillis()-86400*1000);
		
		List<Calendar> calendars = session.createQuery(""
					+ "SELECT s.creationDate "
					+ "FROM Story s  "
					+ "WHERE s.user.id=:user and s.creationDate>=:dtime")
				.setParameter("user",4)
				.setParameter("dtime", yestarday,TemporalType.DATE)
				.list();
		
		session.close();
		Assert.assertEquals(6, calendars.size());
	}
	
	@Test
	public void testRemoveStory() {
		ModelDAO modelDAO = new ModelDAO();
		UserDAO userDAO = new UserDAO();

		StoryDAO storyDAO = new StoryDAO();

		User dragmaf = userDAO.getUserByUsername("dragmaf");
		List<Story> stories = storyDAO.getStoriesById(dragmaf.getId());
		
		Story story = stories.get(0);
		
		modelDAO.delete(Story.class, story.getId());
		
		Assert.assertEquals(true, modelDAO.delete(Story.class, story.getId()));
		
	}

}
