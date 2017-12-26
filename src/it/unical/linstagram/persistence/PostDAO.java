package it.unical.linstagram.persistence;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import it.unical.linstagram.helper.ListHelper;
import it.unical.linstagram.model.Comment;
import it.unical.linstagram.model.Hashtag;
import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.Story;
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

	public List<Post> getPosts() {
		Session session = HibernateUtil.getHibernateSession();
	
		List<Post> posts = session.createQuery("FROM Post p join fetch p.media").list();
		
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
	
	public List<Post> getFollowedPosts(String username) {
		Session session = HibernateUtil.getHibernateSession();

		List<User> followedUsers = session.createQuery("SELECT u.followings FROM User u WHERE u.username=:username")
				.setParameter("username", username).list();
		List<Post> postUser = session.createQuery("SELECT u.posts FROM User u WHERE u.username=:username")
				.setParameter("username", username).list();
		List<Post> posts = new ArrayList<>();
		if(!followedUsers.isEmpty())
			posts = session.createQuery("SELECT p FROM Post p  WHERE p.user in (:fUsers) order by p.postDate desc")
				.setParameter("fUsers",followedUsers).list();
		
		session.close();
		return ListHelper.union(posts, postUser);
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
	
}
