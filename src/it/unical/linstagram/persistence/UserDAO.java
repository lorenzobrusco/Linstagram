package it.unical.linstagram.persistence;

import java.util.Collection;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import it.unical.linstagram.model.Hashtag;
import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;

@Repository
@SuppressWarnings("unchecked")
public class UserDAO implements IUserDAO {

	private static final String USER_EDGE_NGRAM_INDEX = "edgeNGramUsername";

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

	public void inizializeLists(String username) {
		Session session = HibernateUtil.getHibernateSession();
		User user = (User) session.createQuery("FROM  User u where u.username=:username")
				.setParameter("username", username).uniqueResult();

		Hibernate.initialize(user.getPosts());
		Hibernate.initialize(user.getTagged());
		Hibernate.initialize(user.getBookmarks());

		session.close();
	}

	@Override
	public List<User> getSuggestions(String queryString) {
		Session session = HibernateUtil.getHibernateSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);

		QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(User.class).get();
		org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().onFields(USER_EDGE_NGRAM_INDEX).matching(queryString).createQuery();

		// wrap Lucene query in a javax.persistence.Query
		org.hibernate.search.FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, User.class);
		fullTextQuery.setMaxResults(5);

		List<User> users = fullTextQuery.list();

		fullTextSession.close();

		return users;
	}

}