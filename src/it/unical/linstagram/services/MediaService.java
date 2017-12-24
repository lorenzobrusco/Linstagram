package it.unical.linstagram.services;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import it.unical.linstagram.helper.FileModel;
import it.unical.linstagram.model.Media;
import it.unical.linstagram.model.User;

@Service
public class MediaService {

	@Autowired
	private ServletContext context;

	private String nameFile;
	
	/**
	 * From a single uploaded file create a media and save it on disk
	 * @param multipartFile the uploaded File
	 * @param session an HttpSession
	 * @return Media
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Media createMedia(MultipartFile multipartFile, HttpSession session) throws FileNotFoundException, IOException {
		saveFileToLocalDisk(multipartFile,session);
		return getUploadedMediaInfo(multipartFile,session);
	}

	/**
	 * Save media on disk
	 * @param multipartFile
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private void saveFileToLocalDisk(MultipartFile multipartFile, HttpSession session) throws IOException, FileNotFoundException {
		final String outputFileName = getOutputFilename(multipartFile, session);
		FileCopyUtils.copy(multipartFile.getBytes(), new FileOutputStream(outputFileName));
	}
	
	/**
	 * Return a complete Media object created form the uploaded File
	 */
	private Media getUploadedMediaInfo(MultipartFile multipartFile, HttpSession session) throws IOException {
		Media media = new Media();
		String path=getOnlineLocation(session)+nameFile;
		System.out.println(path);
		media.setUrl(path);
		return media;
	}


	/**
	 * Get absolute path with file name
	 * @param multipartFile
	 * @return
	 */
	private String getOutputFilename(MultipartFile multipartFile, HttpSession session) {
		//TODO change file name
//		System.out.println(multipartFile.getOriginalFilename());
		getNameFile(multipartFile, session);
		String name = nameFile;
		return getLocalDestinationLocation(session) + name;
	}

	
	/**
	 * Create the new name of the file to upload
	 * Split the originalName of Multipartfile only for take the extension
	 * And call the incrementNumber method of FileModel that check if exist the file with only extnsion jpd and png
	 * @param multipartFile
	 * @param session
	 */
	private void getNameFile(MultipartFile multipartFile, HttpSession session) {
		String fileName = multipartFile.getOriginalFilename();
		String[] tokens = fileName.split("\\.(?=[^\\.]+$)");
		
		int name_file = FileModel.incrementNumber(getLocalDestinationLocation(session));
		nameFile = name_file+"."+tokens[1];
	}
	
	/**
	 * Get absolute path and create a folder for each user (if not exist).
	 * @return the local path of the image
	 */
	private String getLocalDestinationLocation(HttpSession session) {
		User user = (User) session.getAttribute("user");
		String path_image = context.getRealPath("/WEB-INF/images/");
		FileModel.createFolder(path_image);
		String path = path_image +user.getUsername()+"/";
//		System.out.println(path);
		FileModel.createFolder(path);
		return path;
	}


	/**
	 * Get url where is possible to finde the image
	 * @return url of the image
	 */
	private String getOnlineLocation(HttpSession session) {
		User user = (User) session.getAttribute("user");
		String onlineContextPath = "images/"+user.getUsername()+"/";
		return onlineContextPath;
	}



}
