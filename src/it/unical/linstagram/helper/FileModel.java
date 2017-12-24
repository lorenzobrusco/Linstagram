package it.unical.linstagram.helper;

import java.io.File;

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
	
}
