package it.unical.linstagram.persistence;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import it.unical.linstagram.model.Story;
import it.unical.linstagram.model.User;

@Repository
public class StoryDAO implements IStoryDAO{

	@Override
	public List<Story> getStoriesByUsername(String username) {
		Calendar now = Calendar.getInstance();
		Calendar dayLimit = Calendar.getInstance();
		dayLimit.setTimeInMillis(now.getTimeInMillis()-86400*1000);
		
		Session session = HibernateUtil.getHibernateSession();

		List<Story> stories = session.createQuery("FROM Story s "
				+ "WHERE s.user.username=:username and s.creationDate >= :dayLimit "
				+ "order by s.creationDate desc ")
				.setParameter("username",username)
				.setParameter("dayLimit", dayLimit)
				.list();
		session.close();
		return stories;
	}

	@Override
	public List<User> getViewersOfStory(int idStory) {
		Session session = HibernateUtil.getHibernateSession();

		List<User> users = session.createQuery("SELECT s.viewers FROM Story s WHERE s.id=:idStory")
				.setParameter("idStory",idStory).list();
		session.close();
		return users;	
	}

	@Override
	public List<Story> getFollowedUsersStoriesByUsername(String username) {
		Calendar now = Calendar.getInstance();
		Calendar dayLimit = Calendar.getInstance();
		dayLimit.setTimeInMillis(now.getTimeInMillis()-86400*1000);
		
		Session session = HibernateUtil.getHibernateSession();
		List<User> followedUsers = session.createQuery("SELECT u.followings FROM User u WHERE u.username=:username")
				.setParameter("username", username).list();
		List<Story> stories = null;
		if(followedUsers.isEmpty())
			stories = session.createQuery("SELECT s FROM Story s  WHERE 1=0").list();
		else
			stories = session.createQuery(""
					+ "SELECT s "
					+ "FROM Story s  "
					+ "WHERE s.user in (:fUsers) and s.creationDate >= :dayLimit "
					+ "order by s.creationDate desc")
				.setParameter("fUsers",followedUsers)
				.setParameter("dayLimit", dayLimit)
				.list();
		
		session.close();
		return stories;
	}

	@Override
	public Story getStoryById(int idStory) {
		Session session = HibernateUtil.getHibernateSession();
		Story story = session.createQuery("SELECT s FROM Story s WHERE s.id=:idStory",Story.class).
				setParameter("idStory", idStory).uniqueResult();
		session.close();
		return story;
	}

	
}
