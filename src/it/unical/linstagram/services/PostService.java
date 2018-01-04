package it.unical.linstagram.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.linstagram.dto.UserDTO;
import it.unical.linstagram.dto.UserPrivateDTO;
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
	
	public List<Post> getLatestPost(User user, Calendar date,int last){
		return postDAO.getLastPosts(user.getUsername(), date, last);
	}
	
	public boolean insertLike(int idPost, String username) {
		Post post = postDAO.getPostById(idPost);
		User u = userDAO.getUserByUsername(username);
		if(post.getLikes().add(u)) {
			if (modelDao.merge(post))
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
	
	public boolean insertComment(int idPost, String username, String contentComment, Calendar date) {
		Post post = postDAO.getPostById(idPost);
		User user = userDAO.getUserByUsername(username);

		Comment comment = new Comment(contentComment, user, post, date);
		post.getComments().add(comment);
		
		if(modelDao.merge(comment))
			return true;
		return false;
	}
	
	public User insertBookmark(String username, int idPost) {
		User u = userDAO.getUserByUsername(username);
		Post post = postDAO.getPostById(idPost);
		u.getBookmarks().add(post);
		
		if(modelDao.merge(u))
			return u;
		return null;
	}
	
	public User removeBookmark(String username, int idPost) {
		Post post = postDAO.getPostById(idPost);
		User u = userDAO.getUserByUsername(username);
		u.getBookmarks().remove(post);
		
		if(modelDao.merge(u))
			return u;
		return null;
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

	public List<UserDTO> getLikesOfPost(int idPost) {
		
		List<User> likes = postDAO.getLikesByPostId(idPost);
		
		List<UserDTO> likesDTO = new ArrayList<>();
		for (User user : likes) {
			System.out.println(user.getUsername());
			likesDTO.add(new UserPrivateDTO(user, false, false, false));
		}
		
		return likesDTO;
	}
	
	
	
}