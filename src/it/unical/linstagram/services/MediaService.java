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
import it.unical.linstagram.model.Media.Media_Type;
import it.unical.linstagram.model.User;

@Service
public class MediaService {

	@Autowired
	private ServletContext context;

	private String nameFile;

	/**
	 * Save photo profile
	 * 
	 * @param multipartFile
	 *            the uploaded File
	 * @param session
	 *            an HttpSession
	 * @return Media
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Media createProfilePhoto(MultipartFile multipartFile, HttpSession session)
			throws FileNotFoundException, IOException {
		saveProfilePhotoToLocalDisk(multipartFile, session);
		return getUploadedMediaInfo(multipartFile, Media_Type.IMAGE, session);
	}

	private void saveProfilePhotoToLocalDisk(MultipartFile multipartFile, HttpSession session)
			throws IOException, FileNotFoundException {
		String fileName = multipartFile.getOriginalFilename();
		String[] tokens = fileName.split("\\.(?=[^\\.]+$)");
		nameFile = "photoProfile" + "." + tokens[1];
		final String outputFileName = getLocalDestinationLocation(session) + nameFile;
		byte[] cropImageSquare = FileModel.cropImageSquare(multipartFile.getBytes());
		FileCopyUtils.copy(cropImageSquare, new FileOutputStream(outputFileName));
	}

	/**
	 * From a single uploaded file create a media and save it on disk
	 * 
	 * @param multipartFile
	 *            the uploaded File
	 * @param session
	 *            an HttpSession
	 * @return Media
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Media createMedia(MultipartFile multipartFile, Media_Type type, HttpSession session)
			throws FileNotFoundException, IOException {
		saveMediaToLocalDisk(multipartFile, session);
		return getUploadedMediaInfo(multipartFile, type, session);
	}

	/**
	 * Save media on disk
	 * 
	 * @param multipartFile
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private void saveMediaToLocalDisk(MultipartFile multipartFile, HttpSession session)
			throws IOException, FileNotFoundException {
		final String outputFileName = getOutputFilename(multipartFile, session);
		FileCopyUtils.copy(multipartFile.getBytes(), new FileOutputStream(outputFileName));
	}

	/**
	 * Return a complete Media object created form the uploaded File
	 */
	private Media getUploadedMediaInfo(MultipartFile multipartFile, Media_Type type, HttpSession session)
			throws IOException {
		Media media = new Media();
		String path = getOnlineLocation(session) + nameFile;
		media.setUrl(path);
		media.setType(type);
		return media;
	}

	/**
	 * Get absolute path with file name
	 * 
	 * @param multipartFile
	 * @return
	 */
	private String getOutputFilename(MultipartFile multipartFile, HttpSession session) {
		getNameFile(multipartFile, session);
		String name = nameFile;
		return getLocalDestinationLocation(session) + name;
	}

	/**
	 * Create the new name of the file to upload Split the originalName of
	 * Multipartfile only for take the extension And call the incrementNumber method
	 * of FileModel that check if exist the file with only extnsion jpd and png
	 * 
	 * @param multipartFile
	 * @param session
	 */
	private void getNameFile(MultipartFile multipartFile, HttpSession session) {
		String fileName = multipartFile.getOriginalFilename();
		String[] tokens = fileName.split("\\.(?=[^\\.]+$)");
		int name_file = FileModel.incrementNumber(getLocalDestinationLocation(session));
		nameFile = name_file + "." + tokens[1];
	}

	/**
	 * Get absolute path and create a folder for each user (if not exist).
	 * 
	 * @return the local path of the image
	 */
	private String getLocalDestinationLocation(HttpSession session) {
		User user = (User) session.getAttribute("user");
		String path_image = context.getRealPath("/WEB-INF/images/");

		String path = path_image + user.getId() + "/";

		FileModel.createFolder(path);
		return path;
	}

	/**
	 * Create default image and folder for user
	 */
	public void createImageDefault() {
		String path_image = context.getRealPath("/WEB-INF/images/");
		FileModel.createFolder(path_image);
		String imageUrl = "https://www.1plusx.com/app/mu-plugins/all-in-one-seo-pack-pro/images/default-user-image.png";
		try {
			FileModel.saveImage(imageUrl, path_image + "/default.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get url where is possible to finde the image
	 * 
	 * @return url of the image
	 */
	private String getOnlineLocation(HttpSession session) {
		User user = (User) session.getAttribute("user");
		String onlineContextPath = "images/" + user.getId() + "/";
		return onlineContextPath;
	}

}
