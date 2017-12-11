package it.unical.linstagram.persistence;

import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unical.linstagram.domain.User;

public class UserDAO implements IUserDAO{

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
			System.out.println("create user");
		} catch (Exception e) {
			transaction.rollback();
		}
	}
	
	@Override
	public User findById(int id) {
		final Session session = SessionManager.getInstance().openSession();
		final String query = "select * from user as u where u.id=?";
		final User result = session.createNativeQuery(query, User.class).setParameter(1, id).uniqueResult();
		session.close();
		return result;
	}
	
	@Override
	public boolean delete(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addFollowing(User requestUser,User user) {

		requestUser.getFollowing().add(user);
		user.getFollowers().add(requestUser);

		//TODO: UPDATE TO DB

		return false;
	}

}
