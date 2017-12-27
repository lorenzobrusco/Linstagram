package it.unical.linstagram.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.linstagram.dto.FollowedUserStoriesDTO;
import it.unical.linstagram.dto.StoryDTO;
import it.unical.linstagram.model.Media;
import it.unical.linstagram.model.Story;
import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.ModelDAO;
import it.unical.linstagram.persistence.StoryDAO;

@Service
public class StoriesService {

	@Autowired
	private StoryDAO storyDAO;
	
	@Autowired
	private ModelDAO modelDAO;
	

	public Collection<FollowedUserStoriesDTO> getFollowedStories(User user) {
	
		Map<Integer, FollowedUserStoriesDTO> usersStories = new HashMap<>();
		
		List<Story> stories = storyDAO.getFollowedUsersStoriesByUsername(user.getUsername());
		
		for (Story story : stories) {
			
			if(!usersStories.containsKey(story.getUser().getId())) {
				usersStories.put(story.getUser().getId(), 
						new FollowedUserStoriesDTO(story.getUser().getUsername(),story.getUser().getPhotoProfile()));
			}

			FollowedUserStoriesDTO userStoriesDTO = usersStories.get(story.getUser().getId());
			
			StoryDTO storyDTO = new StoryDTO(story,story.isAViewer(user));
			userStoriesDTO.addStoryDTO(storyDTO);
		}
		
		return usersStories.values();		 
	}
	
	public void AddViewerToStory(User user, int idStory) {
		Story story = storyDAO.getStoryById(idStory);
		
		if(!story.isAViewer(user))
			story.addViewer(user);
		
		modelDAO.update(story);
	}
	
	public void saveStory(Media media, User user) {
		Story story = new Story(user, media);
		modelDAO.save(story);
	}
}
