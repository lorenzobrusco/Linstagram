package it.unical.linstagram.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.linstagram.model.Post;
import it.unical.linstagram.persistence.UserDAO;

@Service
public class ProfileService {
	
	@Autowired
	private UserDAO userDAO;
	
	
	public List<Post> getPostOfUser(String username) {
		return userDAO.getPostByUsername(username);
	}
	
	

}
