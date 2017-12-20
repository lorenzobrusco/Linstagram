package it.unical.linstagram.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import it.unical.linstagram.helper.FileModel;
import it.unical.linstagram.model.Media;
import it.unical.linstagram.model.User;

@Controller
public class UploadController {

	@Autowired
	private ServletContext context;

	
	/**
	 * Getting uploaded files from request
	 * save of all them and create post
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody List<Media> upload(MultipartHttpServletRequest request, HttpServletResponse response,
			HttpSession session)
			throws IOException {
		final Map<String, MultipartFile> fileMap = request.getFileMap();
		final List<Media> uploadedFiles = new ArrayList<>();
		for (MultipartFile multipartFile : fileMap.values()) {
			saveFileToLocalDisk(multipartFile, session);
			Media fileInfo = getUploadedFileInfo(multipartFile, session);
			fileInfo = saveFileToDatabase(fileInfo);
			uploadedFiles.add(fileInfo);
		}
		return uploadedFiles;
	}

	/**
	 * Save media from disk
	 * @param multipartFile
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private void saveFileToLocalDisk(MultipartFile multipartFile, HttpSession session) throws IOException, FileNotFoundException {
		final String outputFileName = getOutputFilename(multipartFile, session);
		FileCopyUtils.copy(multipartFile.getBytes(), new FileOutputStream(outputFileName));
	}

	/**
	 * 
	 * @param media
	 * @return
	 */
	private Media saveFileToDatabase(Media media) {
		//TODO call DAO and save Media
		return media;

	}

	/**
	 * Get absolute path with file name
	 * @param multipartFile
	 * @return
	 */
	private String getOutputFilename(MultipartFile multipartFile, HttpSession session) {
		//TODO change file name
		return getDestinationLocation(session) + multipartFile.getOriginalFilename();
	}

	/**
	 * Create Media from request
	 * @param multipartFile
	 * @return
	 * @throws IOException
	 */
	private Media getUploadedFileInfo(MultipartFile multipartFile, HttpSession session) throws IOException {
		Media media = new Media();
		media.setUrl(getDestinationLocation(session));
		return media;
	}

	/**
	 * Get absolute path
	 * @return
	 */
	private String getDestinationLocation(HttpSession session) {
		User user = (User) session.getAttribute("user");
		String path = context.getRealPath("/WEB-INF/images/"+user.getUsername());
		System.out.println(path);
		FileModel.createFolder(path);
		
		return path;
	}
}
