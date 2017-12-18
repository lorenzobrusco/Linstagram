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
   
   public void createFolder(String path) {
	   
	   File theDir = new File(path);
	
		// if the directory does not exist, create it
		if (!theDir.exists()) {
		    System.out.println("creating directory: " + theDir.getName());
		    boolean result = false;
		
		    try{
		        theDir.mkdir();
		        result = true;
		    } 
		    catch(SecurityException se){
		        //handle it
		    }        
		    if(result) {    
		        System.out.println("DIR created");  
		    }
		}
   }
   
}
