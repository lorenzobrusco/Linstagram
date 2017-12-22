package it.unical.linstagram.model.test;

import java.util.List;

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
	
	@Test
	public void addStory() {
		
		UserDAO userDAO = new UserDAO();
		
		User ciccio = userDAO.getUserByUsername("ciccio");
		
		Media media = new Media(Media_Type.IMAGE,"https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10810091_1527190460857578_541280638_n.jpg");
		Story story = new Story(ciccio, media);
		
//		User dragmaf = userDAO.getUserByUsername("dragmaf");
//		
//		Media media1 = new Media(Media_Type.VIDEO,"https://instagram.frao1-1.fna.fbcdn.net/t50.2886-16/17886251_1128605603951544_572796556789415936_n.mp4");
//		Story story1 = new Story(dragmaf, media1);
//		
//		User root = userDAO.getUserByUsername("root");
//		
//		Story story2 = new Story(root, media);
//		
//		
		
		ModelDAO modelDAO = new ModelDAO();
		modelDAO.save(story);
//		modelDAO.save(story1);
//		modelDAO.save(story2);
	}
//	@Test
	public void addFollowedUsers() {
		
		UserDAO userDAO = new UserDAO();
		
		User ciccio = userDAO.getUserByUsername("ciccio");
		User pippo = userDAO.getUserByUsername("pippo");
		User dragmaf = userDAO.getUserByUsername("dragmaf");
		
//		Story story = new Story(ciccio, media);
		
		pippo.getFollowings().add(ciccio);
		pippo.getFollowings().add(dragmaf);
		
		ModelDAO modelDAO = new ModelDAO();
		modelDAO.update(pippo);
	}

}
