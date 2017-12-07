package it.unical.linstagram.persistence;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unical.linstagram.domain.User;

public class UserDAO implements IUserDAO {

	private static UserDAO userDAO;
	
	private UserDAO() {
	}
	
	/**
	 * Singleton method
	 * @return
	 */
	public static UserDAO getInstance() {
		if(userDAO == null)
			userDAO = new UserDAO();
		return userDAO;
	}

	@Override
	public void save(User user) {
		final Session session = SessionManager.getInstance().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}

	}

	@Override
	public User fingUserById(String username) {
		final Session session = SessionManager.getInstance().openSession();
		final String query = "select * from user as u where u.username=?";
		final User result = session.createNativeQuery(query, User.class).setParameter(1, username).uniqueResult();
		session.close();
		return result;
		
	}

	@Override
	public List<User> allUsers() {
		final Session session = SessionManager.getInstance().openSession();
		final String query = "select * from user";
		final List<User> result = session.createNativeQuery(query, User.class).list();
		session.close();
		return result;
	}

}
