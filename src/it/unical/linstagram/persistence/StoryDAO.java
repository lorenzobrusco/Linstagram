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
		Session session = HibernateUtil.getHibernateSession();

		List<Story> stories = session.createQuery("FROM Story s WHERE s.user.username=:username")
				.setParameter("username",username).list();
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
		Calendar currentTime = Calendar.getInstance();
		Session session = HibernateUtil.getHibernateSession();

		Query followedUsers = session.createQuery("");
		session.createQuery("SELECT s FROM Stories s WHERE s.user.id in (:fUsers)").setParameter("fUsers",followedUsers.list());
		return null;
	}

}
