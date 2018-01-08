package it.unical.linstagram.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.linstagram.dto.UsersStoriesDTO;
import it.unical.linstagram.dto.UserViewerDTO;
import it.unical.linstagram.dto.StoryDTO;
import it.unical.linstagram.dto.StoryViewerDTO;
import it.unical.linstagram.dto.UserDTO;
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


	public Collection<UsersStoriesDTO> getFollowedStories(User user) {

		Map<Integer, UsersStoriesDTO> usersStories = new HashMap<>();

		List<Story> stories = storyDAO.getFollowedUsersStoriesByUsername(user.getUsername());
		for (Story story : stories) {

			if(!usersStories.containsKey(story.getUser().getId())) {
				usersStories.put(story.getUser().getId(), 
						new UsersStoriesDTO(story.getUser().getUsername(),
								story.getUser().getPhotoProfile(),false));
			}

			UsersStoriesDTO userStoriesDTO = usersStories.get(story.getUser().getId());

			StoryDTO storyDTO = new StoryDTO(story,story.isAViewer(user));
			userStoriesDTO.addStoryDTO(storyDTO);
		}

		UsersStoriesDTO userStoriesDTO = new UsersStoriesDTO(user.getUsername(),user.getPhotoProfile(),true);
		List<Story> userStories = storyDAO.getStoriesByUsername(user.getUsername());
		usersStories.put(user.getId(),userStoriesDTO);
		
		for (Story story : userStories) {
			
			StoryDTO storyDTO = new StoryDTO(story,story.isAViewer(user));
			userStoriesDTO.addStoryDTO(storyDTO);
		}
		return usersStories.values();		 
	}


	public void AddViewerToStory(User user, int idStory) {
		Story story = storyDAO.getStoryById(idStory);

		if(!story.isAViewer(user))
			story.addViewer(user);

		modelDAO.merge(story);
	}

	public StoryDTO saveStory(Media media, User user) {
		Story story = new Story(user, media);
		if(modelDAO.save(story))
			return new StoryDTO(story, false);
		
		return null;
	}
	
	public Collection<StoryViewerDTO> getViewersUserStory(User user){
		
		List<Story> stories = storyDAO.getStoriesById(user.getId());
		List<StoryViewerDTO> storyViewerDTOs = new ArrayList<>();
		
		for(Story story : stories){
			StoryViewerDTO svDTO = new StoryViewerDTO(story.getId());
			
			for(User viewer :story.getViewers()) {
				svDTO.addViewer(new UserViewerDTO(viewer));
			}
			storyViewerDTOs.add(svDTO);
		}
		return storyViewerDTOs;
	}


	public boolean removeStory(int idStory) {
		return modelDAO.delete(Story.class,idStory);
	}
}
