package it.unical.linstagram.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.unical.linstagram.dto.UserResearchDTO;
import it.unical.linstagram.model.Hashtag;
import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.HashtagDAO;
import it.unical.linstagram.persistence.UserDAO;

public class ResearchService {
	
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private HashtagDAO hashtagDAO;
	
	public List<UserResearchDTO> getSuggestionsUsername(String usernameQuery)
	{
		List<User> users = userDAO.getSuggestions(usernameQuery);
		
		List<UserResearchDTO> usersDTO = new ArrayList<>();
		
		for (User user : users) {
			usersDTO.add(new UserResearchDTO(user));	
		}
		
		return usersDTO;
	}
	
	public List<UserResearchDTO> getSuggestionsName(String nameQuery)
	{
		List<User> users = userDAO.getSuggestionsName(nameQuery);
		
		List<UserResearchDTO> usersDTO = new ArrayList<>();
		
		for (User user : users) {
			usersDTO.add(new UserResearchDTO(user));	
		}
		
		return usersDTO;
	}
	
	public List<Hashtag> getSuggestionsHashtag(String nameQuery)
	{
		List<Hashtag> hashtags = hashtagDAO.getSuggestions(nameQuery);
		
		return hashtags;
	}
	
}
