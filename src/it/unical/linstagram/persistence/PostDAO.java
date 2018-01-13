package it.unical.linstagram.persistence;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import it.unical.linstagram.model.Comment;
import it.unical.linstagram.model.Hashtag;
import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;

@Repository
@SuppressWarnings("unchecked")
public class PostDAO implements IPostDAO {

	// @PostConstruct
	// private void init() {
	// User u=new UserDAO().getUserByUsername("ciccio");
	// List<Media> medias=new ArrayList<>();
	// Media m=new Media(Media_Type.IMAGE,
	// "D:\\ProgrammiEclipseJEE\\EnterpriseApplication\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Linstagram\\WEB-INF\\images\\1.jpg");
	// medias.add(m);
	// Post p=new Post(u, medias, Calendar.getInstance(), "PRIMO POST MOLTO BELLO");
	// new ModelDAO().save(p);
	// }

	private static final int MAX_RESULTS_POST = 2;
	private static final int MAX_RESULTS_POST_EXPLORE = 9;
	private static final int MAX_RESULTS_COMMENTS = 2;

	public List<Post> getPosts() {
		Session session = HibernateUtil.getSession();

		List<Post> posts = session
				.createQuery("FROM Post p LEFT OUTER join fetch p.hashtags h order by p.postDate desc").list();

		for (Post post : posts) {
			post.getTags().size();
			post.getHashtags().size();
			post.getMedia().size();
			post.getLikes().size();
		}

		session.close();
		return posts;
	}

	public Post getPostById(int idPost) {
		Session session = HibernateUtil.getSession();

		Post post = session.createQuery("FROM Post p WHERE p.id=:idPost", Post.class).setParameter("idPost", idPost)
				.uniqueResult();
		post.getTags().size();
		post.getHashtags().size();
		post.getMedia().size();
		post.getLikes().size();

		session.close();
		return post;
	}

	public List<Post> getLastPosts(String username, Calendar calendar, int last) {
		Session session = HibernateUtil.getSession();

		List<User> followedUsers = session.createQuery("SELECT u.followings FROM User u WHERE u.username=:username")
				.setParameter("username", username).list();
		List<Post> posts = new ArrayList<>();
		Query query = null;
		if (!followedUsers.isEmpty())
			query = session
					.createQuery("SELECT p FROM Post p "
							+ "WHERE p.user in (:fUsers) or p.user.username=:username order by p.postDate desc")
					.setParameter("fUsers", followedUsers).setParameter("username", username);
		else
			query = session
					.createQuery("SELECT p FROM Post p " + "WHERE p.user.username=:username order by p.postDate desc")
					.setParameter("username", username);

		query.setFirstResult(last);
		query.setMaxResults(MAX_RESULTS_POST);
		posts = query.list();

		for (Post post : posts) {
			post.getTags().size();
			post.getHashtags().size();
			post.getMedia().size();
			post.getLikes().size();
		}

		session.close();

		return posts;
	}

	public List<Post> getPopularPosts(String username, Calendar timeReq, int last) {
		Session session = HibernateUtil.getSession();
		List<User> followedUsers = session.createQuery("SELECT u.followings FROM User u WHERE u.username=:username")
				.setParameter("username", username).list();

		List<Post> posts = new ArrayList<>();
		Query query = null;
		if (!followedUsers.isEmpty())
			query = session.createQuery(
					"SELECT p FROM Post p  WHERE (p.user in (:fUsers) or p.user.username=:username) and p.postDate<=:timeR order by p.likes.size desc, p.postDate desc")
					.setParameter("fUsers", followedUsers).setParameter("username", username).setParameter("timeR",timeReq);
		else
			query = session.createQuery(
					"SELECT p FROM Post p  WHERE p.user.username=:username p.postDate<=:timeR order by p.likes.size desc, p.postDate desc")
					.setParameter("username", username).setParameter("timeR",timeReq);

		query.setFirstResult(last);
		query.setMaxResults(MAX_RESULTS_POST);
		posts = query.list();

		for (Post post : posts) {
			post.getTags().size();
			post.getHashtags().size();
			post.getMedia().size();
			post.getLikes().size();
		}
		session.close();

		return posts;
	}

	public List<Post> getPostsExplorePage(Calendar calendar, int last) {
		Session session = HibernateUtil.getSession();
		List<Post> posts = session
				.createQuery("SELECT p FROM Post p WHERE p.user.privateProfile=false order by p.likes.size desc, p.postDate desc")
				.setFirstResult(last).setMaxResults(MAX_RESULTS_POST_EXPLORE).list();
		for (Post post : posts) {
			post.getTags().size();
			post.getHashtags().size();
			post.getMedia().size();
			post.getLikes().size();
		}
		session.close();

		return posts;
	}

	public List<Post> getFollowedPosts(String username) {
		Session session = HibernateUtil.getSession();

		List<User> followedUsers = session.createQuery("SELECT u.followings FROM User u WHERE u.username=:username")
				.setParameter("username", username).list();
		List<Post> posts = new ArrayList<>();
		if (!followedUsers.isEmpty())
			posts = session.createQuery(
					"SELECT p FROM Post p  WHERE p.user in (:fUsers) or p.user.username=:username order by p.postDate desc")
					.setParameter("fUsers", followedUsers).setParameter("username", username).list();
		else
			posts = session
					.createQuery("SELECT p FROM Post p  WHERE p.user.username=:username order by p.postDate desc")
					.setParameter("username", username).list();

		for (Post post : posts) {
			post.getTags().size();
			post.getHashtags().size();
			post.getMedia().size();
			post.getLikes().size();
		}

		session.close();
		return posts;
	}

	@Override
	public List<User> getLikesByPostId(int idPost) {
		Session session = HibernateUtil.getSession();

		List<User> users = session.createQuery("SELECT post.likes FROM Post post WHERE post.id =:idPost")
				.setParameter("idPost", idPost).list();

		session.close();
		return users;
	}

	public boolean doesTheUserLikeThePost(int idPost, User user) {
		Session session = HibernateUtil.getSession();

		List<User> users = session.createQuery("SELECT post.likes FROM Post post WHERE post.id =:idPost")
				.setParameter("idPost", idPost).list();

		session.close();
		return users.contains(user);
	}

	@Override
	public List<User> getUserTaggedByPostId(int idPost) {
		Session session = HibernateUtil.getSession();
		List<User> users = session.createQuery("SELECT post.tags FROM Post post WHERE post.id =:idPost")
				.setParameter("idPost", idPost).list();

		session.close();
		return users;
	}

	@Override
	public List<Hashtag> getHashtagByPostId(int idPost) {
		Session session = HibernateUtil.getSession();
		List<Hashtag> hashtags = session.createQuery("SELECT post.hashtags FROM Post post WHERE post.id=:idPost")
				.setParameter("idPost", idPost).list();
		session.close();
		return hashtags;
	}

	@Override
	public List<Comment> getCommentByPostId(int idPost) {
		Session session = HibernateUtil.getSession();
		List<Comment> comments = session.createQuery("SELECT post.comments FROM Post post WHERE post.id=:idPost")
				.setParameter("idPost", idPost).list();
		session.close();
		return comments;
	}

	public List<Comment> getCommentByPostId(int idPost, int index) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("FROM Comment c WHERE c.post.id=:idPost order by c.date")
				.setParameter("idPost", idPost);
		query.setFirstResult(index);
		query.setMaxResults(MAX_RESULTS_COMMENTS);
		List<Comment> comments = query.list();
		session.close();
		return comments;
	}

	// TODO MIGLIORARE
	public List<Post> getPostsByHashtag(String hashtag, Calendar calendar, int last) {
		Session session = HibernateUtil.getSession();

		Query query = session.createQuery("FROM Post p join fetch p.hashtags h WHERE p.id in "
				+ "(SELECT p1.id FROM Post p1 join p1.hashtags h1 WHERE h1.hashtag = :_hashtag) order by p.postDate desc")
				.setParameter("_hashtag", hashtag);

		query.setFirstResult(last);
		query.setMaxResults(MAX_RESULTS_POST);
		List<Post> posts = query.list();
		for (Post post : posts) {
			post.getTags().size();
			post.getHashtags().size();
			post.getMedia().size();
			post.getLikes().size();
		}
		session.close();
		return posts;
	}

	
	// TODO MIGLIORARE
		public List<Post> getPublicPostsByHashtag(User user, String hashtag, Calendar calendar, int last) {
			Session session = HibernateUtil.getSession();

			Query query = session.createQuery("FROM Post p join fetch p.hashtags h WHERE (p.user.privateProfile = false "
					+ "or p.user = :_currentUser or :_currentUser member of p.user.followers) and  p.id  in "
					+ "(SELECT p1.id FROM Post p1 join p1.hashtags h1 WHERE h1.hashtag = :_hashtag) order by p.postDate desc")
					.setParameter("_hashtag", hashtag).setParameter("_currentUser", user);

			query.setFirstResult(last);
			query.setMaxResults(MAX_RESULTS_POST);
			List<Post> posts = query.list();
			for (Post post : posts) {
				post.getTags().size();
				post.getHashtags().size();
				post.getMedia().size();
				post.getLikes().size();
			}
			session.close();
			return posts;
		}

}