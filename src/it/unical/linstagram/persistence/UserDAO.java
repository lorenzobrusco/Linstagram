package it.unical.linstagram.persistence;

import java.util.List;

import org.hibernate.Session;

import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;

@SuppressWarnings("unchecked")
public class UserDAO implements IUserDAO {

	// #### SINGLETON ####
	private static UserDAO instance;

	public static UserDAO getInstance() {
		if (instance == null)
			instance = new UserDAO();
		return instance;
	}

	private UserDAO() {
	}
	// ---- SINGLETON ----

	@Override
	public User getUserByUsername(String username) {
		Session session = HibernateUtil.getHibernateSession();
		User user = (User) session.createQuery("FROM  User u where u.username=:username")
				.setParameter("username", username).uniqueResult();
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
		List<Post> posts = session.createQuery("SELECT user.posts FROM User user WHERE user.username=:username")
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
		List<Post> posts = session.createQuery("SELECT user.tagged FROM User user WHERE user.username=:username")
				.setParameter("username", username).list();
		
		session.close();
		return posts;
	}
}
