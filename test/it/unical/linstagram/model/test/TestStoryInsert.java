package it.unical.linstagram.model.test;

import java.util.Calendar;
import java.util.List;

import javax.persistence.TemporalType;

import org.hibernate.Session;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.Story;
import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.HibernateUtil;
import it.unical.linstagram.persistence.PostDAO;
import it.unical.linstagram.persistence.StoryDAO;
import it.unical.linstagram.persistence.UserDAO;

public class TestStoryInsert {
	
	@BeforeClass
	public static void init() {
		HibernateUtil.initSessionFactory(false);
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
		
		Session session = HibernateUtil.getSession();
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
	


}
