package it.unical.linstagram.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.springframework.web.multipart.MultipartFile;

public class FileModel {
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	/**
	 * Create directory whether it doesn't exist
	 * 
	 * @param path
	 */
	public static void createFolder(String path) {

		File theDir = new File(path);

		if (!theDir.exists()) {
			System.out.println("creating directory: " + theDir.getName());
			boolean result = false;

			try {
				theDir.mkdir();
				result = true;
			} catch (SecurityException se) {
				// handle it
			}
			if (result) {
				System.out.println("DIR created");
			}
		}
	}

	
	
	public static int incrementNumber(String dir) {
//		int num = 0;
//		File file = new File(dir, num +"."); 
//
//		for (num = 1; file.exists(); num++) {
//		    file = new File(dir, num + ".");
//		}
		boolean find = true;
		File directory = new File(dir);
		
		if(directory.list().length > 0) {
			for (int num = 0; find; num++) {
				File file_jpg = new File(dir, num+".jpg");
				File file_png = new File(dir, num+".png");
				if (!file_jpg.exists() && !file_png.exists())
					return num;
			}
		}
		
		return 0;
	}
	
	

//	public static void main(String[] args) throws Exception {
//		String imageUrl = "http://www.avajava.com/images/avajavalogo.jpg";
//		String destinationFile = "image.jpg";
//
//		saveImage(imageUrl, destinationFile);
//	}

	public static void saveImage(String imageUrl, String destinationFile) throws IOException {
		File file = new File(destinationFile);
		if (!file.exists()) {
			URL url = new URL(imageUrl);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(destinationFile);
	
			byte[] b = new byte[2048];
			int length;
	
			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}
	
			is.close();
			os.close();
		}
	}
	
}
