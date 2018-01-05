package it.unical.linstagram.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.linstagram.dto.UserViewerDTO;
import it.unical.linstagram.model.Hashtag;
import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.HashtagDAO;
import it.unical.linstagram.persistence.UserDAO;

@Service
public class ResearchService {
	
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private HashtagDAO hashtagDAO;
	
	public Set<UserViewerDTO> getSuggestionsUsername(String usernameQuery)
	{
		List<User> users = userDAO.getSuggestions(usernameQuery);
		
		Set<UserViewerDTO> usersDTO = new HashSet<>();
		
		for (User user : users) {
			usersDTO.add(new UserViewerDTO(user));	
		}
		
		return usersDTO;
	}
	
	public Set<UserViewerDTO> getSuggestionsName(String nameQuery)
	{
		List<User> users = userDAO.getSuggestionsName(nameQuery);
		
		Set<UserViewerDTO> usersDTO = new HashSet<>();
		
		for (User user : users) {
			usersDTO.add(new UserViewerDTO(user));	
		}
		
		return usersDTO;
	}
	
	public Set<Hashtag> getSuggestionsHashtag(String nameQuery)
	{
		List<Hashtag> hashtags = hashtagDAO.getSuggestions(nameQuery);
		
		return new HashSet<>(hashtags);
	}
	
}
