package it.unical.linstagram.controllers;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import it.unical.linstagram.helper.FileModel;

@Controller
public class UploadController {

	@Autowired
	ServletContext context;

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public ModelAndView fileUploadPage() {
		System.out.println("here");
		FileModel file = new FileModel();
		ModelAndView modelAndView = new ModelAndView("fileUpload", "command", file);
		return modelAndView;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String fileUpload(@Validated FileModel file, Model model) throws IOException {
		System.out.println("here");
//		FileModel file = new FileModel();
//		if (result.hasErrors()) {
//			System.out.println("validation errors");
//			return "fileUploadPage";
//		} else {
			System.out.println("Fetching file");
			MultipartFile multipartFile = file.getFile();
			String uploadPath = context.getRealPath("") + File.separator + "WEB-INF" + File.separator + "images"
					+ File.separator;
			file.createFolder(uploadPath);
			file.setFile(multipartFile);
			System.out.println(file.getFile());
			// Now do something with file...
			FileCopyUtils.copy(file.getFile().getBytes(), new File(uploadPath + file.getFile().getOriginalFilename()));
			String fileName = multipartFile.getOriginalFilename();
			model.addAttribute("fileName", fileName);
			return "success";
//		}
	}
}
