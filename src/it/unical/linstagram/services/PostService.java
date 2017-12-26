package it.unical.linstagram.services;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.linstagram.helper.HashtagFinder;
import it.unical.linstagram.helper.TagFinder;
import it.unical.linstagram.model.Comment;
import it.unical.linstagram.model.Hashtag;
import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.Story;
import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.HashtagDAO;
import it.unical.linstagram.persistence.HibernateUtil;
import it.unical.linstagram.persistence.ModelDAO;
import it.unical.linstagram.persistence.PostDAO;
import it.unical.linstagram.persistence.UserDAO;

@Service
public class PostService {

	@Autowired
	private PostDAO postDAO;
	@Autowired
	private ModelDAO modelDao;	
	@Autowired
	private HashtagDAO hashtagDAO;
	@Autowired
	private UserDAO userDAO;
	
	public List<Post> getPosts() {
		return postDAO.getPosts();
	}

	public Post getPost(int idPost) {
		return postDAO.getPostById(idPost);
	}
	
	public boolean insertLike(int idPost, String username) {
		Post post = postDAO.getPostById(idPost);
		User u = userDAO.getUserByUsername(username);
		if(post.getLikes().add(u)) {
//			post.getLikes().clear();
			if (modelDao.update(post))
				return true;
		}
		return false;
	}
	
	public boolean removeLike(String username, int idPost) {
		Post post = postDAO.getPostById(idPost);
		User u = userDAO.getUserByUsername(username);
		post.getLikes().remove(u);
		
		if(modelDao.update(post))
			return true;
		return false;
	}
	
	public boolean insertComment(int idPost, Comment comment) {
		Post post = postDAO.getPostById(idPost);
		post.getComments().add(comment);
		
		if(modelDao.save(comment))
			return true;
		return false;
	}
	
	public boolean insertBookmark(String username, int idPost) {
		User u = userDAO.getUserByUsername(username);
		Post post = postDAO.getPostById(idPost);
		u.getBookmarks().add(post);
		
		if(modelDao.merge(u))
			return true;
		return false;
	}
	
	public boolean removeBookmark(String username, int idPost) {
		Post post = postDAO.getPostById(idPost);
		User u = userDAO.getUserByUsername(username);
		u.getBookmarks().remove(post);
		
		if(modelDao.update(u))
			return true;
		return false;
	}
	
	public void savePost(Post post) {
		
		List<String> findHashtags = HashtagFinder.findHashtags(post.getContent());
		List<String> findTags = TagFinder.findTags(post.getContent());
		
		for (String fh : findHashtags) {
			Hashtag hashtagByValue = hashtagDAO.getHashtagByValue(fh);
			if (hashtagByValue != null)
			{
				hashtagByValue.setCount(hashtagByValue.getCount()+1);
				modelDao.update(hashtagByValue);
				
			}
			else
			{
				hashtagByValue = new Hashtag(fh.toLowerCase(), 1);
			}
			post.getHashtags().add(hashtagByValue);
		}
		
		for (String tag : findTags) {
			User userByUsername = userDAO.getUserByUsername(tag);
			if (userByUsername != null)
			{
				post.getTags().add(userByUsername);
			}
		}
		
		
		modelDao.merge(post);
	}
	
	public List<Post> getFollowedPosts(String username) {
		return postDAO.getFollowedPosts(username);
	}
	
	
	
}