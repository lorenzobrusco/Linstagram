package it.unical.linstagram.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import it.unical.linstagram.dto.NotificationDTO;
import it.unical.linstagram.dto.PostDTO;
import it.unical.linstagram.dto.StoryDTO;
import it.unical.linstagram.dto.StoryViewerDTO;
import it.unical.linstagram.dto.UserViewerDTO;
import it.unical.linstagram.helper.MessageResponse;
import it.unical.linstagram.helper.UserManager;
import it.unical.linstagram.model.Hashtag;
import it.unical.linstagram.model.Media;
import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.UserDAO;
import it.unical.linstagram.services.MediaService;
import it.unical.linstagram.services.MessageCode;
import it.unical.linstagram.services.NotificationService;
import it.unical.linstagram.services.PostService;
import it.unical.linstagram.services.ResearchService;
import it.unical.linstagram.services.StoriesService;

@Controller
public class HomePageController {

	@Autowired
	private PostService postService;

	@Autowired
	private MediaService uploadService;

	@Autowired
	private StoriesService storiesService;

	@Autowired
	private NotificationService notificationService;

	@Autowired 
	private ResearchService researchService;

	@Autowired
	UserDAO userDAO;

	@RequestMapping("/index")
	public String homePageController(HttpSession session, Model model) {
		if (UserManager.checkLogged(session)) {
			final User loggedUser = (User) session.getAttribute("user");
			//List<Post> posts = postService.getFollowedPosts(loggedUser.getUsername());
//			final List<Post> posts = postService.getPosts();
			List<PostDTO> posts = postService.getLatestPost(loggedUser,null, 0);
			final List<NotificationDTO> notifications = notificationService.getAllNotificationToSee(loggedUser, 10);
			model.addAttribute("posts", posts);
			model.addAttribute("notifications", notifications);
			model.addAttribute("followedUsersStories", storiesService.getFollowedStories(loggedUser));
			return "index";
		}
		return "redirect:/";

	}

	@RequestMapping("/storyViewed")
	public String viewStory(HttpSession session, @RequestParam int idStory) {
		if (UserManager.checkLogged(session)) {
			User loggedUser = (User) session.getAttribute("user");
			storiesService.AddViewerToStory(loggedUser, idStory);
			return "redirect:index";
		}
		return "redirect:index";
	}

	// TODO: hande multiple file
	// /**
	// * Getting uploaded files from request
	// * save of all them and create post
	// * @param request
	// * @param response
	// * @return
	// * @throws IOException
	// */
	// @RequestMapping(value = "/upload", method = RequestMethod.POST)
	// public @ResponseBody List<Media> multipleUpload(MultipartHttpServletRequest
	// request, HttpServletResponse response,
	// HttpSession session)
	// throws IOException {
	// final Map<String, MultipartFile> fileMap = request.getFileMap();
	// final List<Media> uploadedFiles = new ArrayList<>();
	// for (MultipartFile multipartFile : fileMap.values()) {
	// saveFileToLocalDisk(multipartFile, session);
	// Media fileInfo = getUploadedFileInfo(multipartFile, session);
	// fileInfo = saveFileToDatabase(fileInfo);
	// uploadedFiles.add(fileInfo);
	// }
	// return uploadedFiles;
	// }

	@ResponseBody
	@RequestMapping(value = "/createPost", method = RequestMethod.POST)
	public Media createPost(@RequestParam String postDescription, @RequestParam MultipartFile file, HttpSession session)
			throws IOException {
		Media mediaInfo = uploadService.createMedia(file, session);
		final List<Media> uploadedFiles = new ArrayList<>();
		uploadedFiles.add(mediaInfo);
		Post new_post = new Post((User) (session.getAttribute("user")), uploadedFiles, Calendar.getInstance(),
				postDescription);
		postService.savePost(new_post);
		return mediaInfo;
	}

	@RequestMapping("/latestPost")
	public String latestPost(@RequestParam int last,@RequestParam long time,HttpSession session,Model model) {
		User loggedUser = (User) session.getAttribute("user");

		List<PostDTO> posts = postService.getLatestPost(loggedUser,null, last);
		model.addAttribute("posts", posts);
		return "fragment/post";
	}
	@ResponseBody
	@RequestMapping(value = "/addStory", method = RequestMethod.POST)
	public StoryDTO addStory(@RequestParam MultipartFile file, HttpSession session) throws IOException {

		Media mediaStory = uploadService.createMedia(file, session);
		StoryDTO storyDTO = storiesService.saveStory(mediaStory, (User) session.getAttribute("user"));
		return storyDTO;
	}

	@ResponseBody
	@RequestMapping(value = "/storiesViewer")
	public Collection<StoryViewerDTO> getStoriesViewer(HttpSession session) {
		Collection<StoryViewerDTO> storyViewerDTOs = storiesService
				.getViewersUserStory((User) session.getAttribute("user"));
		return storyViewerDTOs;
	}

	@RequestMapping(value = "/deleteStory",method=RequestMethod.POST)
	@ResponseBody
	public String removeStory(@RequestParam int idStory, HttpSession session) {
		if(storiesService.removeStory(idStory))
			return new MessageResponse(MessageCode.OK,(User) session.getAttribute("user"),"OK").getMessage();

		return new MessageResponse(MessageCode.FAILED,
				(User) session.getAttribute("user"),"Non � stato possibile rimuovere la storia.").getMessage();
	}


	@RequestMapping(value="/research",method=RequestMethod.POST)
	@ResponseBody
	public String research(@RequestParam String text, HttpSession session) {
		Set<Hashtag> suggestionsHashtag = researchService.getSuggestionsHashtag(text);
		Set<UserViewerDTO> suggestionsUsers = researchService.getSuggestionsUsername(text);
		suggestionsUsers.addAll(researchService.getSuggestionsName(text));

		for (Hashtag hashtag : suggestionsHashtag) {
			System.out.println(hashtag.getHashtag());
		}

		session.setAttribute("hashtagSearch", suggestionsHashtag);
		session.setAttribute("userSearch", suggestionsUsers);


		return "SUCCESS";

	}


	@RequestMapping(value="hashtagPosts",method=RequestMethod.POST)
	public String hashtagPosts(@RequestParam String hashtag, HttpSession session, Model model) {
		List<PostDTO> posts = postService.getPostsbyHashtag((User)session.getAttribute("user"), hashtag);
		model.addAttribute("posts", posts);
		System.out.println(posts.size());
		
		//TODO SISTEMARE IL METODO
		return "fragment/post";


	}

}
