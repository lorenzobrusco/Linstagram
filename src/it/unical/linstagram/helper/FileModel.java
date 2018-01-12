package it.unical.linstagram.helper;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
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
		File directory = new File(dir);
		return directory.list().length +1;

	}

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

	public static byte[] cropImageSquare(byte[] image) throws IOException {
		InputStream in = new ByteArrayInputStream(image);
		BufferedImage originalImage = ImageIO.read(in);
		
		BufferedImage resizedImg=resize(originalImage);
		

		// Get image dimensions
		int height = resizedImg.getHeight();
		int width = resizedImg.getWidth();

		// The image is already a square
		if (height == width) {
			return BufferedImageToByteArray(resizedImg);
		}
		
		// Compute the size of the square
		int squareSize = (height > width ? width : height);

		// Coordinates of the image's middle
		int xc = width / 2;
		int yc = height / 2;

		// Crop
		BufferedImage croppedImage = resizedImg.getSubimage(xc - (squareSize / 2), // x coordinate of the upper-left
																						// corner
				yc - (squareSize / 2), // y coordinate of the upper-left corner
				squareSize, // widht
				squareSize // height
		);

		return BufferedImageToByteArray(croppedImage);
//		return BufferedImageToByteArray(resizedImg);

	}

	private static BufferedImage resize(BufferedImage orImage) {
		final int MAX_WIDTH = 1366;
		final int MAX_HEIGHT = 768;
		
		Dimension newMaxSize = new Dimension(MAX_WIDTH, MAX_HEIGHT);
		BufferedImage resizedImg = Scalr.resize(orImage, Method.QUALITY, newMaxSize.width, newMaxSize.height);
		
		return resizedImg;

//		int height = orImage.getHeight();
//		int width = orImage.getWidth();
//
//		if (height < MAX_HEIGHT && width < MAX_WIDTH) {
//			return orImage;
//		}
//
//		if (width > height) {
//			if (width > MAX_WIDTH) {
//				height *= MAX_WIDTH / width;
//				width = MAX_WIDTH;
//			}
//		} else {
//			if (height > MAX_HEIGHT) {
//				width *= MAX_HEIGHT / height;
//				height = MAX_HEIGHT;
//			}
//		}
//
//		BufferedImage outputImage = new BufferedImage(MAX_WIDTH, MAX_HEIGHT, orImage.getType());
//		
//		Graphics2D g2d = outputImage.createGraphics();
//        g2d.drawImage(orImage, 0, 0, MAX_WIDTH, MAX_HEIGHT, null);
//        g2d.dispose();
// 
//        return outputImage;
	}

	private static byte[] BufferedImageToByteArray(BufferedImage orImage) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(orImage, "jpg", baos);
			byte[] imageBytes = baos.toByteArray();
			// do something with the byte array
			return imageBytes;
		} catch (IOException ie) {
			throw new RuntimeException("Error convert BufferedImage to Bytes");
		}
	}

}
