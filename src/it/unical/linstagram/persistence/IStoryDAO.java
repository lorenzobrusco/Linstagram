package it.unical.linstagram.persistence;

import java.util.List;

import it.unical.linstagram.model.Story;
import it.unical.linstagram.model.User;

public interface IStoryDAO {

	List<Story> getStoriesByUsername(String username);
	List<User> getViewersOfStory(int id);
	List<Story> getFollowedUsersStoriesByUsername(String username);
	Story getStoryById(int idStory);
	List<Story> getStoriesById(int idUser);
}
