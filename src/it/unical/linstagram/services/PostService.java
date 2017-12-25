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
	
	public void insertLike(int idPost, User user) {
		Post post = postDAO.getPostById(idPost);
		post.getLikes().add(user);
		
		modelDao.update(post);
	}
	
	public void removeLike(int idPost, User user) {
		Post post = postDAO.getPostById(idPost);
		post.getLikes().remove(user);
		
		modelDao.update(post);
	}
	
	public void insertComment(int idPost, Comment comment) {
		Post post = postDAO.getPostById(idPost);
		System.out.println("POST2 "+post);

		post.getComments().add(comment);
		
		modelDao.update(post);
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