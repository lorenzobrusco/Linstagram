package it.unical.linstagram.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
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

import it.unical.linstagram.helper.ListHelper;
import it.unical.linstagram.helper.UserManager;
import it.unical.linstagram.model.Media;
import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.UserDAO;
import it.unical.linstagram.services.MediaService;
import it.unical.linstagram.services.PostService;
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
	UserDAO userDAO;
	
	@RequestMapping("/index")
	public String homePageController(HttpSession session, Model model) {
		if(UserManager.checkLogged(session)) {
			List<Post> posts = postService.getFollowedPosts(((User)session.getAttribute("user")).getUsername());
			ListHelper.order(posts);
			
			model.addAttribute("posts", posts);
			model.addAttribute("followedUsersStories",storiesService.getFollowedStories((User)session.getAttribute("user")));
			return "index";
		}
		return "redirect:/";
		
	}
	
	@RequestMapping("/storyViewed")
	public String viewStory(HttpSession session, @RequestParam int idStory) {
		if(UserManager.checkLogged(session)) {
			User loggedUser = (User) session.getAttribute("user");
			storiesService.AddViewerToStory(loggedUser, idStory);
			return "redirect:index";
		}
		return "redirect:index";
	}
	
	
	//TODO: hande multiple file
//	/**
//	 * Getting uploaded files from request
//	 * save of all them and create post
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "/upload", method = RequestMethod.POST)
//	public @ResponseBody List<Media> multipleUpload(MultipartHttpServletRequest request, HttpServletResponse response,
//			HttpSession session)
//			throws IOException {
//		final Map<String, MultipartFile> fileMap = request.getFileMap();
//		final List<Media> uploadedFiles = new ArrayList<>();
//		for (MultipartFile multipartFile : fileMap.values()) {
//			saveFileToLocalDisk(multipartFile, session);
//			Media fileInfo = getUploadedFileInfo(multipartFile, session);
//			fileInfo = saveFileToDatabase(fileInfo);
//			uploadedFiles.add(fileInfo);
//		}
//		return uploadedFiles;
//	}
	
	
	@ResponseBody
	@RequestMapping(value ="/createPost", method = RequestMethod.POST)
	public Media createPost(@RequestParam String postDescription,@RequestParam MultipartFile file,HttpSession session) throws IOException {
		Media mediaInfo = uploadService.createMedia(file, session);
		final List<Media> uploadedFiles = new ArrayList<>();
		uploadedFiles.add(mediaInfo);
		Post new_post = new Post((User)(session.getAttribute("user")), uploadedFiles, Calendar.getInstance(), postDescription);
		postService.savePost(new_post);
		return mediaInfo;
	}
	
	@RequestMapping(value ="/addStory", method = RequestMethod.POST)
	public String addStory(@RequestParam MultipartFile file,HttpSession session) throws IOException {
		
		Media mediaStory = uploadService.createMedia(file, session);
		storiesService.saveStory(mediaStory, (User) session.getAttribute("user"));
		return "redirect:index";
	}
	
	
	
}
	