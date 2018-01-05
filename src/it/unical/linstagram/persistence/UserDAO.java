package it.unical.linstagram.persistence;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import it.unical.linstagram.model.Hashtag;
import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.RequestFollow;
import it.unical.linstagram.model.User;

@Repository
@SuppressWarnings("unchecked")
public class UserDAO implements IUserDAO {

	private static final String USERNAME_EDGE_NGRAM_INDEX = "edgeNGramUsername";
	private static final String NAME_EDGE_NGRAM_INDEX = "edgeNGramName";
	private static final String SURNAME_EDGE_NGRAM_INDEX = "edgeNGramSurname";

	@Override
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

	@Override
	public List<User> getFollowingByUsername(String username) {
		Session session = HibernateUtil.getHibernateSession();
		List<User> users = session.createQuery("SELECT user.followings FROM User user WHERE user.username=:username")
				.setParameter("username", username).list();

		session.close();
		return users;
	}

	@Override
	public List<User> getFollowerByUsername(String username) {
		Session session = HibernateUtil.getHibernateSession();
		List<User> users = session.createQuery("SELECT user.followers FROM User user WHERE user.username=:username")
				.setParameter("username", username).list();

		session.close();
		return users;
	}
	
	@Override
	public int searchRequestFollow(String usernameFrom, String usernameTo) {
		Session session = HibernateUtil.getHibernateSession();
		
		int id = -1;		
		if (existRequestFollow(usernameFrom, usernameTo)) {
			id = (int) session.createQuery("SELECT r.id FROM RequestFollow r where r.userFrom.username=:usernameFrom "
				+ "AND r.userTo.username=:usernameTo")
					.setParameter("usernameFrom", usernameFrom).setParameter("usernameTo", usernameTo)
					.uniqueResult();
		}
		
		session.close();
		return id;
	}

	@Override
	public boolean existRequestFollow(String usernameFrom, String usernameTo) {
		Session session = HibernateUtil.getHibernateSession();
		
		boolean exist = (Long) session.createQuery("SELECT count(*) FROM RequestFollow r where r.userFrom.username=:usernameFrom "
				+ "AND r.userTo.username=:usernameTo")
					.setParameter("usernameFrom", usernameFrom).setParameter("usernameTo", usernameTo)
					.uniqueResult() > 0;
	
		session.close();
		return exist;
	}
		
	@Override
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

	@Override
	public void inizializeListUser(Set<?> set) {
		Session session = HibernateUtil.getHibernateSession();

		Hibernate.initialize(set);

		session.close();
	}






	@Override
	public List<User> getSuggestions(String queryString) {
		Session session = HibernateUtil.getHibernateSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);

		QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder()
				.forEntity(User.class).get();
		org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword()
				.onFields(USERNAME_EDGE_NGRAM_INDEX).matching(queryString).createQuery();


		// wrap Lucene query in a javax.persistence.Query
		org.hibernate.search.FullTextQuery fullTextQuery = fullTextSession
				.createFullTextQuery(luceneQuery, User.class);
		fullTextQuery.setMaxResults(5);

		List<User> users = fullTextQuery.list();

		fullTextSession.close();

		return users;
	}

	@Override
	public List<User> getSuggestionsName(String queryString) {
		Session session = HibernateUtil.getHibernateSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);

		QueryBuilder queryBuilder = fullTextSession.getSearchFactory()
				.buildQueryBuilder().forEntity(User.class).get();
		org.apache.lucene.search.Query luceneQuery = queryBuilder.bool()
				.should(queryBuilder.keyword().onField(NAME_EDGE_NGRAM_INDEX).matching(queryString).createQuery())
				.should(queryBuilder.keyword().onField(SURNAME_EDGE_NGRAM_INDEX).matching(queryString).createQuery())
				.createQuery();


		// wrap Lucene query in a javax.persistence.Query
		org.hibernate.search.FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, User.class);
		fullTextQuery.setMaxResults(5);

		List<User> users = fullTextQuery.list();

		fullTextSession.close();

		return users;
	}

}

