package it.unical.linstagram.persistence;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import it.unical.linstagram.domain.User;

public class UserDao implements IUserDAO{

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
	public User fingUserById(int id) {
		// TODO Auto-generated method stub
		return null;
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
