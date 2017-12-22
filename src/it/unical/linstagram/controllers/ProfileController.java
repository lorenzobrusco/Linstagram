package it.unical.linstagram.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.unical.linstagram.helper.UserManager;
import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;
import it.unical.linstagram.services.ProfileService;

@Controller
public class ProfileController {

	@Autowired
	private ProfileService profileService;
	
	@RequestMapping("profile")
	public String getSignInPage(HttpSession session, Model model) {
		if(UserManager.checkLogged(session)) {
			User user = (User) session.getAttribute("user");
			List<Post> postOfUser = profileService.getPostOfUser(user.getUsername());
			
			model.addAttribute("posts", postOfUser);
			return "profile";
		}
		return "redirect:/";
	}

	@RequestMapping("modifyProfile")
	public String getModifyProfile(HttpSession session) {
		if(UserManager.checkLogged(session))
			return "modify_profile";
		return "redirect:/";
	}

	@RequestMapping("/sendInfoProfile")
	@ResponseBody
	public String setInfoProfile(HttpSession session, @RequestParam("name") String name,
			@RequestParam("username") String username, @RequestParam("email") String email,
			@RequestParam("sesso") String gender, @RequestParam("date") String date, @RequestParam("bio") String bio,
			@RequestParam("privateCheck") String privateCheck) {
		
		User user = (User) session.getAttribute("user");
		if (!name.equals(""))
			if (!profileService.changeName(user, name))
				return "NAME_FAILED";
		if (!username.equals("")) {
			if (!profileService.changeUsername(user, username))
				return "USERNAME_FAILED";
		}
		if (!email.equals("")) {
			if (!profileService.changeEmail(user, email))
				return "EMAIL_FAILED";
		}
		if (!gender.equals("-1"))
			if (!profileService.changeGender(user, gender))
				return "GENDER_FAILED";
		
		if (!date.equals("")) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date dateNew = sdf.parse(date);
				Calendar cal = Calendar.getInstance();
				cal.setTime(dateNew);
				if (!cal.before(Calendar.getInstance()))
					return "VIENI DAL FUTURO??";
				
				if (!profileService.changeDate(user, cal))
					return "DATE_FAILED";
				
			} catch (ParseException e) {
				return "DATE_FAIL";
			}
		}
		
		if (!bio.equals(""))
			if (!profileService.changeBiography(user, bio))
				return "BIO_FAILED";
		
		if (!profileService.changePrivateField(user, privateCheck))
			return "PRIVATE_FAILED";
		
		return "OK";
	}
	
	@RequestMapping("taggedPhoto")
	public String getTaggedPhoto(HttpSession session, Model model) {
		if(UserManager.checkLogged(session)) {
			User user = (User) session.getAttribute("user");
			List<Post> postOfUser = profileService.getPostTaggedOfUser(user.getUsername());
			
			model.addAttribute("posts", postOfUser);
			return "fragment/profilePost";	//profilePost.jsp
		}
		return "redirect:/";
	}

	@RequestMapping("bookmarkPhoto")
	public String getBookmarkPhoto(HttpSession session, Model model) {
		if(UserManager.checkLogged(session)) {
			User user = (User) session.getAttribute("user");
			List<Post> postOfUser = profileService.getBookmarkOfUser(user.getUsername());
			
			model.addAttribute("posts", postOfUser);
			return "fragment/profilePost";	//profilePost.jsp
		}
		return "redirect:/";
	}
}
