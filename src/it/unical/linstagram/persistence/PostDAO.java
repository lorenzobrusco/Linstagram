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


	//	@PostConstruct
	//	private void init() {
	//		User u=new UserDAO().getUserByUsername("ciccio");
	//		List<Media> medias=new ArrayList<>();
	//		Media m=new Media(Media_Type.IMAGE, "D:\\ProgrammiEclipseJEE\\EnterpriseApplication\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Linstagram\\WEB-INF\\images\\1.jpg");
	//		medias.add(m);
	//		Post p=new Post(u, medias, Calendar.getInstance(), "PRIMO POST MOLTO BELLO");
	//		new ModelDAO().save(p);
	//	}

	private static final int MAX_RESULTS = 2;

	public List<Post> getPosts() {
		Session session = HibernateUtil.getHibernateSession();

		List<Post> posts = session.createQuery("FROM Post p LEFT OUTER join fetch p.hashtags h order by p.postDate desc").list();

		session.close();
		return posts;
	}

	public Post getPostById(int idPost) {
		Session session = HibernateUtil.getHibernateSession();

		Post post = session.createQuery("FROM Post p WHERE p.id=:idPost", Post.class)
				.setParameter("idPost", idPost).uniqueResult();

		session.close();
		return post;
	}
	public List<Post> getLastPosts(String username,Calendar calendar, int last){
		Session session = HibernateUtil.getHibernateSession();

		List<User> followedUsers = session.createQuery("SELECT u.followings FROM User u WHERE u.username=:username")
				.setParameter("username", username).list();
		List<Post> posts = new ArrayList<>();
		Query query = null;
		if(!followedUsers.isEmpty())
			query = session.createQuery("SELECT p FROM Post p  WHERE p.user in (:fUsers) or p.user.username=:username order by p.postDate desc")
			.setParameter("fUsers",followedUsers).setParameter("username", username);
		else
			query = session.createQuery("SELECT p FROM Post p  WHERE p.user.username=:username order by p.postDate desc")
			.setParameter("username", username);

		query.setFirstResult(last*MAX_RESULTS);
		query.setMaxResults(MAX_RESULTS);
		posts = query.list();
		session.close();

		return posts;
	}

	public List<Post> getFollowedPosts(String username) {
		Session session = HibernateUtil.getHibernateSession();

		List<User> followedUsers = session.createQuery("SELECT u.followings FROM User u WHERE u.username=:username")
				.setParameter("username", username).list();
		List<Post> posts = new ArrayList<>();
		if(!followedUsers.isEmpty())
			posts = session.createQuery("SELECT p FROM Post p  WHERE p.user in (:fUsers) or p.user.username=:username order by p.postDate desc")
			.setParameter("fUsers",followedUsers).setParameter("username", username).list();
		else
			posts = session.createQuery("SELECT p FROM Post p  WHERE p.user.username=:username order by p.postDate desc")
			.setParameter("username", username).list();
		session.close();
		return posts;
	}


	@Override
	public List<User> getLikesByPostId(int idPost) {
		Session session = HibernateUtil.getHibernateSession();

		List<User> users = session.createQuery("SELECT post.likes FROM Post post WHERE post.id =:idPost")
				.setParameter("idPost", idPost).list();

		session.close();
		return users;
	}

	@Override
	public List<User> getUserTaggedByPostId(int idPost) {
		Session session = HibernateUtil.getHibernateSession();
		List<User> users =session.createQuery("SELECT post.tags FROM Post post WHERE post.id =:idPost")
				.setParameter("idPost", idPost).list();

		session.close();
		return users;
	}

	@Override
	public List<Hashtag> getHashtagByPostId(int idPost) {
		Session session = HibernateUtil.getHibernateSession();
		List<Hashtag> hashtags = session.createQuery("SELECT post.hashtags FROM Post post WHERE post.id=:idPost")
				.setParameter("idPost", idPost).list();
		session.close();
		return hashtags;
	}

	@Override
	public List<Comment> getCommentByPostId(int idPost) {
		Session session = HibernateUtil.getHibernateSession();
		List<Comment> comments = session.createQuery("SELECT post.comments FROM Post post WHERE post.id=:idPost")
				.setParameter("idPost", idPost).list();
		session.close();
		return comments;
	}


	public List<Post> getPostsByHashtag(Hashtag hashtag) {
		Session session = HibernateUtil.getHibernateSession();

		List<Post> posts = session.createQuery("FROM Post p join fetch p.hashtags h WHERE h.hashtag=:_hashtag")
				.setParameter("_hashtag", hashtag).list();

		session.close();
		return posts;
	}

}
