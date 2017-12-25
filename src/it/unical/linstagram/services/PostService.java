package it.unical.linstagram.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.linstagram.helper.HashtagFinder;
import it.unical.linstagram.helper.TagFinder;
import it.unical.linstagram.model.Comment;
import it.unical.linstagram.model.Hashtag;
import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.HashtagDAO;
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
	
	public boolean insertLike(int idPost, User user) {
		Post post = postDAO.getPostById(idPost);
		if(post.getLikes().add(user)) {
			post.getLikes().clear();
			modelDao.update(post);
			if (modelDao.merge(user))
				return true;
		}
		return false;
	}
	
	public void removeLike(int idPost, User user) {
		Post post = postDAO.getPostById(idPost);
		post.getLikes().remove(user);
		
		modelDao.update(post);
	}
	
	public boolean insertComment(int idPost, Comment comment) {
		Post post = postDAO.getPostById(idPost);
		post.getComments().add(comment);
		
		if(modelDao.save(comment))
			return true;
		return false;
	}
	
	public boolean insertBookmark(User user, int idPost) {
		User userDB = userDAO.getUserByUsername(user.getUsername());
		Post post = postDAO.getPostById(idPost);
		if(user.getBookmarks().add(post)) {
			//TODO chiedere a Bernardo! facendo solo update non andava... ma perchè??? ho dovuto fare sta schifezza
			userDB.getBookmarks().clear();
			modelDao.update(userDB);
			if(modelDao.merge(user)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean removeBookmark(User user, int idPost) {
		//TODO implementare non appena ci sarà il popup del post [si può mettere un bottone]
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
				hashtagByValue = new Hashtag(fh, 1);
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
		
		
		modelDao.save(post);
	}
	
}