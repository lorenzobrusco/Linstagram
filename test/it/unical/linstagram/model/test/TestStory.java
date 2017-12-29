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

public class TestStory extends AbstractModelTest {

//	@Autowired
//	ModelDAO modelDAO;
//	
//	@Autowired
//	StoryDAO storyDAO;
//	

		
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
	
	@Test
	public void getFollowedUserStories() {
		User eliana = new User("Eliana","email","pass");
		User manuel = new User("Manuel","e","pass");
		User paola = new User("Paola","paola","pass");
		
		Media media = new Media(Media_Type.IMAGE,"urlmedia");
		Story story = new Story(eliana, media);
		Story story2 = new Story(eliana, media);
		
		Media media1 = new Media(Media_Type.IMAGE,"urlmedia");
		Story story1 = new Story(manuel, media1);
		
		paola.getFollowings().add(eliana);
		paola.getFollowings().add(manuel);
		
		story.getViewers().add(manuel);
		story.getViewers().add(paola);

		ModelDAO modelDAO = new ModelDAO();

		modelDAO.save(eliana);
		modelDAO.save(manuel);
		modelDAO.save(paola);
		
		modelDAO.save(story);
		modelDAO.save(story1);
		modelDAO.save(story2);
		
		
		IStoryDAO storyDAO = new StoryDAO();
		List<Story> stories = storyDAO.getFollowedUsersStoriesByUsername(paola.getUsername());
		
		Assert.assertEquals(3, stories.size());
//		Assert.assertEquals(2, stories.get(0).getViewers().size());

		
	}
	
	@Test
	public void testEmptyStories() {
		User eliana = new User("Eliana","email","pass");
		
		ModelDAO modelDAO = new ModelDAO();
		modelDAO.save(eliana);
		
		IStoryDAO storyDAO = new StoryDAO();
		List<Story> stories = storyDAO.getFollowedUsersStoriesByUsername(eliana.getUsername());
	}
	
	@Test
	public void testGetStoriesById() {
		User eliana = new User("Eliana","email","pass");
		
		Media media = new Media(Media_Type.IMAGE,"urlmedia");
		
		Story story = new Story(eliana, media);
		
		ModelDAO modelDAO = new ModelDAO();
		
		modelDAO.save(eliana);
		modelDAO.save(story);
		
		IStoryDAO storyDAO = new StoryDAO();
		Story s = storyDAO.getStoryById(story.getId());
		Assert.assertNotNull(s);
	}
}
