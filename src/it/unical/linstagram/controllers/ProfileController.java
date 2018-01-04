package it.unical.linstagram.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import it.unical.linstagram.helper.EncryptPassword;
import it.unical.linstagram.helper.MessageResponse;
import it.unical.linstagram.helper.UserManager;
import it.unical.linstagram.model.Gender;
import it.unical.linstagram.model.Media;
import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;
import it.unical.linstagram.services.MediaService;
import it.unical.linstagram.services.MessageCode;
import it.unical.linstagram.services.ProfileService;
import it.unical.linstagram.services.UserService;

@Controller
public class ProfileController {

	@Autowired
	private ProfileService profileService;
	@Autowired
	private UserService userService;
	@Autowired
	private MediaService uploadService;
	
	
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
			return "modifyProfile";
		return "redirect:/";
	}

	
//Controllo dei campi che l'utente cambia
	@RequestMapping("/sendInfoProfile")
	@ResponseBody
	public String setInfoProfile(HttpSession session, @RequestParam("name") String name, @RequestParam("surname") String surname,
			@RequestParam("username") String username, @RequestParam("email") String email,
			@RequestParam("sesso") String gender, @RequestParam("date") String date, @RequestParam("bio") String bio,
			@RequestParam("privateCheck") String privateCheck) {
		
		User user = (User) session.getAttribute("user");

		if (!name.equals(""))
			user.setName(name);
		
		if (!surname.equals(""))
			user.setSurname(surname);
		
		if (!username.equals("")) {
			if (!profileService.changeUsername(username))
				return  new MessageResponse(MessageCode.USERNAME_FAILED, user, "USERNAME_FAILED").getMessage();
			user.setUsername(username);
		}
		
		if (!email.equals("")) {
			if (!profileService.changeEmail(email))
				return  new MessageResponse(MessageCode.EMAIL_FAILED, user, "EMAIL_FAILED").getMessage();
			user.setEmail(email);
		}
		
		if (!gender.equals("-1"))
			user.setGender(Gender.values()[Integer.parseInt(gender)-1]);
		
		if (!date.equals("")) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				Date dateNew = sdf.parse(date);
				Calendar cal = Calendar.getInstance();
				cal.setTime(dateNew);
				if (!cal.before(Calendar.getInstance()))
					return new MessageResponse(MessageCode.FAILED, user, "Mi stai dicendo che vieni dal futuro?").getMessage();

				user.setBirthdate(cal);
				
			} catch (ParseException e) {
				return  new MessageResponse(MessageCode.FAILED, user, "Non è stato possibile cambiare la data di nascita.").getMessage();
			}
		}
		
		if (!bio.equals(""))
			user.setBiography(bio);

		if (!privateCheck.equals(""))
			if (privateCheck.equals("true"))
				user.setPrivateProfile(true);
			else 
				user.setPrivateProfile(false);
		
		if (!profileService.updateUser(user))
			return  new MessageResponse(MessageCode.FAILED, user, "FAILED").getMessage();
		
		return  new MessageResponse(MessageCode.OK, user, "OK").getMessage();
	}
	
	@RequestMapping("sendChangePassword")
	@ResponseBody
	public String changePassword(HttpSession session, @RequestParam("old_pass") String old_password, 
			 @RequestParam("new_pass") String new_password,  @RequestParam("repeat_pass") String repeat_password) {
		
		User user = (User) session.getAttribute("user");
		String pass = profileService.getPassword(user.getUsername());
		
		if (!pass.equals(EncryptPassword.checkPassword(old_password, pass)))
			return  new MessageResponse(MessageCode.PASS_WRONG, user, "La password inserita non corrisponde alla password corrente.").getMessage();
		
		if (!new_password.equals(repeat_password))
			return  new MessageResponse(MessageCode.PASS_DIFFERENT, user, "Le due password inserite sono diverse.").getMessage();
		
		String password = EncryptPassword.encrypt(new_password);
		profileService.changePassword(user, password);
		
		return "OK";
	}
	
	
// Per switchare la grafica di "modify_info" a "modify_password"
	@RequestMapping("changePasswordPage")
	public String getPagePassword(HttpSession session) {
		return "fragment/modifyProfileFragment/modifyPasswordSection";
	}

	@RequestMapping("changeInfoUserPage")
	public String getInfoUser(HttpSession session) {
		return "fragment/modifyProfileFragment/modifyProfileSection";
	}
	
	@RequestMapping(value ="uploadPhotoProfile", method = RequestMethod.POST)
	public String uploadProfilePhoto(@RequestParam MultipartFile file,HttpSession session) throws FileNotFoundException, IOException {
		Media mediaInfo = uploadService.createProfilePhoto(file, session);
		User user = (User) session.getAttribute("user");
		user.setPhotoProfile(mediaInfo.getUrl());
		System.out.println(user.getPhotoProfile());
		boolean result = profileService.uploadPhotoProfile(user);
		if(result)
			return "modifyProfile";
		else
			return "redirect:/";
	}
	
	
// Controllo sugli eventi quando vengono richieste le foto in cui gli utenti sono taggati e i bookmarks	

	@RequestMapping("postPhoto")
	public String getPostPhoto(HttpSession session, Model model, @RequestParam("username") String username) {
		if(UserManager.checkLogged(session)) {
			User user = userService.getUser(username);
			List<Post> postOfUser = profileService.getPostOfUser(user.getUsername());
			model.addAttribute("posts", postOfUser);
			return "fragment/userProfileFragment/postSection";	//Per aggiungere solo i post in cui e' taggato l'utente [utilizzato sia per utente nella sessione che per gli altri utenti]
		}
		return "redirect:/";
	}
	
	@RequestMapping("taggedPhoto")
	public String getTaggedPhoto(HttpSession session, Model model, @RequestParam("username") String username) {
		if(UserManager.checkLogged(session)) {
			User user = userService.getUser(username);
			List<Post> postOfUser = profileService.getPostTaggedOfUser(user.getUsername());
			model.addAttribute("posts", postOfUser);
			return "fragment/userProfileFragment/taggedPhotoSection";	//Per aggiungere solo i post in cui e' taggato l'utente [utilizzato sia per utente nella sessione che per gli altri utenti]
		}
		return "redirect:/";
	}

	@RequestMapping("bookmarkPhoto")
	public String getBookmarkPhoto(HttpSession session, Model model) {
		if(UserManager.checkLogged(session)) {
			User user = (User) session.getAttribute("user");
			List<Post> postOfUser = profileService.getBookmarkOfUser(user.getUsername());
			
			model.addAttribute("posts", postOfUser);
			return "fragment/userProfileFragment/bookmarkPhotoSection";	
		}
		return "redirect:/";
	}
}
