package it.unical.linstagram.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.unical.linstagram.dto.ResearchDTO;
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


	public List<ResearchDTO> generalQuery(String queryString)
	{
		List<User> users = userDAO.getSuggestions(queryString);
		users.addAll(userDAO.getSuggestionsName(queryString));
		HashSet<User> uniqueValues = new HashSet<>(users);

		List<Hashtag> hashtags = hashtagDAO.getSuggestions(queryString);

		int maxUsers=-1;
		int maxHashtags =-1;

		if (uniqueValues.size() >2 && hashtags.size() >2)
		{
			maxHashtags= 3;
			maxUsers = 3;
		}
		else if (uniqueValues.size() <=2 && hashtags.size() >2)
		{
			maxUsers = uniqueValues.size();
			maxHashtags= 6 - maxUsers;
		}
		else if (uniqueValues.size() >2 && hashtags.size() <=2)
		{
			maxHashtags= hashtags.size();
			maxUsers = 6 - maxHashtags;
		}else {
			maxHashtags= hashtags.size();
			maxUsers = uniqueValues.size();
		}

		List<ResearchDTO> researchDTOs = new ArrayList<>();
		int count =0;
		for (Iterator<User> iterator = uniqueValues.iterator(); iterator.hasNext() && count<maxUsers;count++)
		{
			researchDTOs.add(new ResearchDTO(iterator.next()));
			
		}
		count =0;
		for (Iterator<Hashtag> iterator = hashtags.iterator(); iterator.hasNext() && count<maxHashtags;count++) 
		{
			researchDTOs.add(new ResearchDTO(iterator.next()));
		}
		
		return researchDTOs;
	
	}


	public JsonArray userQuery(String queryString)
	{
		List<User> users = userDAO.getSuggestions(queryString);
		users.addAll(userDAO.getSuggestionsName(queryString));
		HashSet<User> uniqueValues = new HashSet<>(users);

		JsonArray jsonArray = new JsonArray();
		if (uniqueValues.size() >= 5)
			jsonArray.addAll(jsonForUser(uniqueValues, 5));
		else
			jsonArray.addAll(jsonForUser(uniqueValues, uniqueValues.size()));

		return jsonArray;
	}

	public JsonArray hashtagQuery(String queryString)
	{
		List<Hashtag> hashtags = hashtagDAO.getSuggestions(queryString);

		JsonArray jsonArray = new JsonArray();
		if (hashtags.size() >= 5)
			jsonArray.addAll(jsonForHashtag(hashtags, 5));
		else
			jsonArray.addAll(jsonForHashtag(hashtags, hashtags.size()));

		return jsonArray;
	}
	private JsonArray jsonForUser (HashSet<User> uniqueValues, int maxNumber)
	{
		JsonArray jsonArray = new JsonArray(maxNumber);
		int count =0;
		for (Iterator<User> iterator = uniqueValues.iterator(); iterator.hasNext() && count<maxNumber;count++) {

			User u = (User) iterator.next();
			JsonObject obj = new JsonObject();
			obj.addProperty("key", u.getUsername());
			String title = "";

			if (u.getName() != null )
			{
				title = title+u.getName();
				if (u.getSurname() != null)
					title = title+" "+ u.getSurname();
			}
			else if (u.getSurname() != null)
				title = title+u.getSurname();

			obj.addProperty("title", title);

			obj.addProperty("iconUrl", u.getPhotoProfile());

			jsonArray.add(obj);
		}
		return jsonArray;

	}

	private JsonArray jsonForHashtag (List<Hashtag> hashtags, int maxNumber)
	{
		JsonArray jsonArray = new JsonArray(maxNumber);
		int count =0;
		for (Iterator<Hashtag> iterator = hashtags.iterator(); iterator.hasNext() && count<maxNumber;count++) {

			Hashtag h = (Hashtag) iterator.next();
			JsonObject obj = new JsonObject();
			obj.addProperty("key", "#"+h.getHashtag());

			obj.addProperty("title", h.getCount()+ " posts");
			//TODO
			obj.addProperty("iconUrl", "undefined");

			jsonArray.add(obj);
		}

		return jsonArray;

	}

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
