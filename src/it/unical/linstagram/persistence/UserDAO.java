package it.unical.linstagram.persistence;

import org.hibernate.Session;

import it.unical.linstagram.model.User;

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
		//TODO: Eliana -> crittografia
		Session session = HibernateUtil.getHibernateSession();
		User user = (User) session.createQuery("FROM  User u where u.username=:username and u.password=:password")
				.setParameter("username", username).setParameter("password", password).uniqueResult();
		session.close();
		return user;
	}

	public User getUserEmailAndPass(String email, String password) {
		//TODO: Eliana -> crittografia
		Session session = HibernateUtil.getHibernateSession();
		User user = (User) session.createQuery("FROM  User u where u.email=:email and u.password=:password")
				.setParameter("email", email).setParameter("password", password).uniqueResult();
		session.close();
		return user;
	}

}
