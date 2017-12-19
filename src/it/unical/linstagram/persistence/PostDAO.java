package it.unical.linstagram.persistence;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import it.unical.linstagram.model.Comment;
import it.unical.linstagram.model.Hashtag;
import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;

public class PostDAO implements IPostDAO {

	// #### SINGLETON ####
	private static PostDAO instance;

	public static PostDAO getInstance() {
		if (instance == null)
			instance = new PostDAO();
		return instance;
	}

	private PostDAO() {
	}
	// ---- SINGLETON ----
	
	
	@Override
	public List<User> getLikesByPostId(int idPost) {
		Session session = HibernateUtil.getHibernateSession();
		List<User> users = (List<User>) session.createQuery("SELECT post.likes FROM Post post WHERE post.id =:idPost")
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
	
}
