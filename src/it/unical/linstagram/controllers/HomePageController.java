package it.unical.linstagram.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import it.unical.linstagram.dto.PostDTO;
import it.unical.linstagram.dto.ResearchDTO;
import it.unical.linstagram.dto.StoryDTO;
import it.unical.linstagram.dto.StoryViewerDTO;
import it.unical.linstagram.helper.MessageResponse;
import it.unical.linstagram.helper.UserManager;
import it.unical.linstagram.model.Media;
import it.unical.linstagram.model.Media.Media_Type;
import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;
import it.unical.linstagram.services.MediaService;
import it.unical.linstagram.services.MessageCode;
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
	private ResearchService researchService;

	@RequestMapping("/")
	public String homePageController(HttpSession session, Model model) {

		if (UserManager.checkLogged(session)) {
			final User loggedUser = (User) session.getAttribute("user");

			List<PostDTO> posts = postService.getLatestPost(loggedUser, null, 0);

			model.addAttribute("posts", posts);
			model.addAttribute("followedUsersStories", storiesService.getFollowedStories(loggedUser));
			return "index";
		}
		return "index";

	}

	@RequestMapping("/explore")
	public String explorePage(HttpSession session, Model model) {
		if (UserManager.checkLogged(session)) {
			User loggedUser = (User) session.getAttribute("user");
			List<PostDTO> posts = new ArrayList<>();
			posts = postService.getPopularPostsExplorePage(loggedUser, null, 0);
			model.addAttribute("posts", posts);
			return "explore";
		}
		return "redirect:/";
	}

	@RequestMapping("/storyViewed")
	public String viewStory(HttpSession session, @RequestParam int idStory) {
		if (UserManager.checkLogged(session)) {
			User loggedUser = (User) session.getAttribute("user");
			storiesService.AddViewerToStory(loggedUser, idStory);
			return "redirect:/";
		}
		return "redirect:/";
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
	public Media createPost(@RequestParam String postDescription, @RequestParam String type,
			@RequestParam MultipartFile file, HttpSession session) throws IOException {
		Media mediaInfo = null;
		if (type.equals("image"))
			mediaInfo = uploadService.createMedia(file, Media_Type.IMAGE, session);
		else
			mediaInfo = uploadService.createMedia(file, Media_Type.VIDEO, session);
		final List<Media> uploadedFiles = new ArrayList<>();
		uploadedFiles.add(mediaInfo);
		Post new_post = new Post((User) (session.getAttribute("user")), uploadedFiles, Calendar.getInstance(),
				postDescription);
		postService.savePost(new_post);
		return mediaInfo;
	}

	@RequestMapping("/getPosts")
	public String popularPosts(@RequestParam long time, @RequestParam int lastIndex,
			@RequestParam("type") String typeRequest, HttpSession session, Model model) {

		User loggedUser = (User) session.getAttribute("user");
		List<PostDTO> posts = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		if (typeRequest.equals("latest"))
			posts = postService.getLatestPost(loggedUser, cal, lastIndex);
		else if (typeRequest.equals("popular"))
			posts = postService.getPopularPosts(loggedUser, cal, lastIndex);

		session.setAttribute("postRequest", typeRequest);
		model.addAttribute("posts", posts);

		return "fragment/post";
	}

	@RequestMapping("/getPostsExplore")
	public String popularPostsExplore(@RequestParam long time, @RequestParam int lastIndex,
			@RequestParam("type") String typeRequest, HttpSession session, Model model) {

		User loggedUser = (User) session.getAttribute("user");
		List<PostDTO> posts = new ArrayList<>();
		posts = postService.getPopularPostsExplorePage(loggedUser, null, lastIndex);
		model.addAttribute("posts", posts);
		return "fragment/userProfileFragment/postSection";
	}

	//
	// @RequestMapping("/otherPosts")
	// public String otherPosts(@RequestParam int last,@RequestParam long
	// time,HttpSession session,Model model) {
	// User loggedUser = (User) session.getAttribute("user");
	//
	// List<PostDTO> posts = null;
	// if(session.getAttribute("postRequest").equals("latest"))
	// posts = postService.getLatestPost(loggedUser,null, last);
	// else if(session.getAttribute("postRequest").equals("popular"))
	// posts = postService.getPopularPosts(loggedUser, null, last);
	//
	// model.addAttribute("posts", posts);
	// return "fragment/post";
	// }
	//
	@ResponseBody
	@RequestMapping(value = "/addStory", method = RequestMethod.POST)
	public StoryDTO addStory(@RequestParam MultipartFile file, HttpSession session) throws IOException {

		Media mediaStory = uploadService.createMedia(file, Media_Type.IMAGE, session);
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

	@RequestMapping(value = "/deleteStory", method = RequestMethod.POST)
	@ResponseBody
	public String removeStory(@RequestParam int idStory, HttpSession session) {
		if (storiesService.removeStory(idStory))
			return new MessageResponse(MessageCode.OK, (User) session.getAttribute("user"), "OK").getMessage();

		return new MessageResponse(MessageCode.FAILED, (User) session.getAttribute("user"),
				"Non e' stato possibile rimuovere la storia.").getMessage();
	}

	@ResponseBody
	@RequestMapping(value = "/research", method = RequestMethod.POST)
	public List<ResearchDTO> research(@RequestParam String text, HttpSession session) {
		// Set<Hashtag> suggestionsHashtag =
		// researchService.getSuggestionsHashtag(text);
		// Set<UserViewerDTO> suggestionsUsers =
		// researchService.getSuggestionsUsername(text);
		// suggestionsUsers.addAll(researchService.getSuggestionsName(text));
		//
		// for (Hashtag hashtag : suggestionsHashtag) {
		// System.out.println(hashtag.getHashtag());
		// }
		//
		// session.setAttribute("hashtagSearch", suggestionsHashtag);
		// session.setAttribute("userSearch", suggestionsUsers);
		//
		//
		// return "SUCCESS";
		// TODO DA SISTEMARE
		System.out.println(text);
		List<ResearchDTO> generalQuery = researchService.generalQuery(text);
		System.out.println(generalQuery);
		return generalQuery;
	}

}
