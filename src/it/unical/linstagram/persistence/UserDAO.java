package it.unical.linstagram.persistence;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;

@Repository
@SuppressWarnings("unchecked")
public class UserDAO implements IUserDAO {

	public List<User> getAllUser() {
		Session session = HibernateUtil.getHibernateSession();
		List<User> users = session.createQuery("FROM  User").list();
		session.close();
		return users;
	}
	
	@Override
	public User getUserByUsername(String username) {
		Session session = HibernateUtil.getHibernateSession();
		User user = (User) session.createQuery("FROM  User u where u.username=:username")
				.setParameter("username", username).uniqueResult();
//		Hibernate.initialize(user.getPosts());
		
		session.close();
		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		Session session = HibernateUtil.getHibernateSession();
		User user = (User) session.createQuery("FROM  User u where u.email=:email")
				.setParameter("email", email).uniqueResult();
		session.close();
		return user;
	}

	@Override
	public User getUserByUsernameAndPass(String username, String password) {
		Session session = HibernateUtil.getHibernateSession();
		User user = (User) session.createQuery("FROM  User u where u.username=:username and u.password=:password")
				.setParameter("username", username).setParameter("password", password).uniqueResult();
		session.close();
		return user;
	}

	@Override
	public User getUserEmailAndPass(String email, String password) {
		Session session = HibernateUtil.getHibernateSession();
		User user = (User) session.createQuery("FROM  User u where u.email=:email and u.password=:password")
				.setParameter("email", email).setParameter("password", password).uniqueResult();
		session.close();
		return user;
	}
	
	@Override
	public String getPasswordByUsername(String username) {
		Session session = HibernateUtil.getHibernateSession();
		String pass = session.createQuery("SELECT u.password FROM  User u where u.username=:username", String.class)
				.setParameter("username", username).uniqueResult();
		
		session.close();
		return pass;
	
	}

	@Override
	public String getPasswordByEmail(String email) {
		Session session = HibernateUtil.getHibernateSession();
		String pass = session.createQuery("SELECT u.password FROM  User u where u.email=:email", String.class)
				.setParameter("email", email).uniqueResult();
		
		session.close();
		return pass;
	}
	
	@Override
	public List<Post> getPostByUsername(String username) {
		Session session = HibernateUtil.getHibernateSession();
		List<Post> posts = session.createQuery("SELECT user.posts FROM User user, Post p JOIN FETCH p.media WHERE user.username=:username")
				.setParameter("username", username).list();
		
		session.close();
		return posts;
	}
	
	@Override
	public List<Post> getBookmarksByUsername(String username) {
		Session session = HibernateUtil.getHibernateSession();
		List<Post> posts = session.createQuery("SELECT user.bookmarks FROM User user WHERE user.username=:username")
				.setParameter("username", username).list();
		
		session.close();
		return posts;
	}
	
	@Override
	public List<Post> getTaggedPostByUsername(String username) {
		Session session = HibernateUtil.getHibernateSession();
		List<Post> posts = session.createQuery("SELECT user.tagged FROM User user, Post p JOIN FETCH p.media WHERE user.username=:username")
				.setParameter("username", username).list();
		
		session.close();
		return posts;
	}
	
	
	public List<User> getFollowingByUsername(String username) {
		Session session = HibernateUtil.getHibernateSession();
		List<User> users = session.createQuery("SELECT user.followings FROM User user WHERE user.username=:username")
				.setParameter("username", username).list();
		
		session.close();
		return users;
	}
	
	public User inizializeLists(String username) {
		Session session = HibernateUtil.getHibernateSession();
		User user = (User) session.createQuery("FROM  User u where u.username=:username")
				.setParameter("username", username).uniqueResult();
		
		Hibernate.initialize(user.getPosts());
		Hibernate.initialize(user.getTagged());
		Hibernate.initialize(user.getBookmarks());
		Hibernate.initialize(user.getFollowings());
		Hibernate.initialize(user.getFollowers());
		
		session.close();
		return user;
	}
	
	public void inizializeListUser(Set<Post> set) {
		Session session = HibernateUtil.getHibernateSession();
		
		Hibernate.initialize(set);
		
		session.close();
		
	}
	
	
}
