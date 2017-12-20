package it.unical.linstagram.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.linstagram.model.Comment;
import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.ModelDAO;
import it.unical.linstagram.persistence.PostDAO;

@Service
public class PostService {

	@Autowired
	private PostDAO postDAO;
	
	
	
	public List<Post> getPosts() {
		return postDAO.getPosts();
	}

	public Post getPost(int idPost) {
		return postDAO.getPostById(idPost);
	}
	
	public void insertLike(int idPost, User user) {
		Post post = postDAO.getPostById(idPost);
		post.getLikes().add(user);
		
		new ModelDAO().update(post);
	}
	
	public void insertComment(int idPost, Comment comment) {
		Post post = postDAO.getPostById(idPost);
		System.out.println("POST2 "+post);

		post.getComments().add(comment);
		
		new ModelDAO().update(post);
	}
	
}
