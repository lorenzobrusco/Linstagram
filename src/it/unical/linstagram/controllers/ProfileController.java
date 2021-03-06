package it.unical.linstagram.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import it.unical.linstagram.config.CustomPasswordEncoder;
import it.unical.linstagram.dto.PostPreviewDTO;
import it.unical.linstagram.helper.MessageResponse;
import it.unical.linstagram.model.Gender;
import it.unical.linstagram.model.Media;
import it.unical.linstagram.model.User;
import it.unical.linstagram.services.HashtagService;
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
	@Autowired
	private HashtagService hashtagsService;

	@RequestMapping("profile")
	public String getProfilePage(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");

		List<PostPreviewDTO> postOfUser = profileService.getPostOfUser(user.getUsername(),0);
		model.addAttribute("posts", postOfUser);
		int postCount = profileService.getPostCount(user.getId());
		model.addAttribute("postsCount", postCount);
		
		model.addAttribute("numberOfFollowings", userService.getCountFollowings(user.getId()));
		model.addAttribute("numberOfFollowers", userService.getCountFollowers(user.getId()));
		

		return "profile";
	}

	@RequestMapping("modifyProfile")
	public String getModifyProfile(HttpSession session) {
		return "modifyProfile";
	}

	// Controllo dei campi che l'utente cambia
	@RequestMapping("/sendInfoProfile")
	@ResponseBody
	public String setInfoProfile(HttpSession session, @RequestParam("name") String name,
			@RequestParam("surname") String surname, @RequestParam("username") String username,
			@RequestParam("email") String email, @RequestParam("sesso") String gender,
			@RequestParam("date") String date, @RequestParam("bio") String bio,
			@RequestParam("privateCheck") String privateCheck) {

		User user = (User) session.getAttribute("user");

		if (!name.equals(""))
			user.setName(name);

		if (!surname.equals(""))
			user.setSurname(surname);

		if (!username.equals("")) {
			if (!profileService.changeUsername(username))
				return new MessageResponse(MessageCode.USERNAME_FAILED, user, "USERNAME_FAILED").getMessage();
			user.setUsername(username);
		}

		if (!email.equals("")) {
			if (!profileService.changeEmail(email))
				return new MessageResponse(MessageCode.EMAIL_FAILED, user, "EMAIL_FAILED").getMessage();
			user.setEmail(email);
		}

		if (!gender.equals("-1"))
			user.setGender(Gender.values()[Integer.parseInt(gender) - 1]);

		if (!date.equals("")) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				Calendar cal = Calendar.getInstance();
				cal.setTime(sdf.parse(date));
				if (!cal.before(Calendar.getInstance()))
					return new MessageResponse(MessageCode.FAILED, user, "Mi stai dicendo che vieni dal futuro?")
							.getMessage();

				user.setBirthdate(cal);

			} catch (ParseException e) {
				return new MessageResponse(MessageCode.FAILED, user,
						"Non è stato possibile cambiare la data di nascita.").getMessage();
			}
		}

		if (!bio.equals(""))
			user.setBiography(bio);

		boolean updateHashtagsCount = false;

		if (!privateCheck.equals("")) {
			if (!user.isPrivateProfile() && privateCheck.equals("true") 
					|| user.isPrivateProfile() && privateCheck.equals("false"))
				updateHashtagsCount = true;

			if (privateCheck.equals("true"))
				user.setPrivateProfile(true);
			else
				user.setPrivateProfile(false);
		}
		MessageResponse messageResponse = null;
		if (!profileService.updateUser(user))
			messageResponse = new MessageResponse(MessageCode.FAILED, user, "FAILED");
		else
			messageResponse = new MessageResponse(MessageCode.OK, user, "OK");

		if (updateHashtagsCount)
			hashtagsService.modifyCounterPerUser(user.getUsername(), user.isPrivateProfile());

		return messageResponse.getMessage();
	}

	@RequestMapping("sendChangePassword")
	@ResponseBody
	public String changePassword(HttpSession session, @RequestParam("old_pass") String old_password,
			@RequestParam("new_pass") String new_password, @RequestParam("repeat_pass") String repeat_password) {

		User user = (User) session.getAttribute("user");
		CustomPasswordEncoder passwordEncoder = new CustomPasswordEncoder();

		String pass = profileService.getPassword(user.getUsername());

		if (!passwordEncoder.matches(old_password, pass))
			return new MessageResponse(MessageCode.PASS_WRONG, user,
					"La password inserita non corrisponde alla password corrente.").getMessage();

		if (!new_password.equals(repeat_password))
			return new MessageResponse(MessageCode.PASS_DIFFERENT, user, "Le due password inserite sono diverse.")
					.getMessage();

		String password = passwordEncoder.encode(new_password);
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

	@RequestMapping(value = "uploadPhotoProfile", method = RequestMethod.POST)
	public String uploadProfilePhoto(@RequestParam MultipartFile file, HttpSession session)
			throws FileNotFoundException, IOException {
		Media mediaInfo = uploadService.createProfilePhoto(file, session);
		User user = (User) session.getAttribute("user");
		user.setPhotoProfile(mediaInfo.getUrl());

		boolean result = profileService.uploadPhotoProfile(user);
		if (result)
			return "modifyProfile";
		else
			return "redirect:/";
	}

	// Controllo sugli eventi quando vengono richieste le foto in cui gli utenti
	// sono taggati e i bookmarks

	@RequestMapping("postPhoto")
	public String getPostPhoto(HttpSession session, Model model, @RequestParam("username") String username,@RequestParam("lastIndex") int lastIndex) {
		User user = userService.getUser(username);
		List<PostPreviewDTO> postOfUser = profileService.getPostOfUser(user.getUsername(),lastIndex);
		model.addAttribute("posts", postOfUser);
		return "fragment/userProfileFragment/postSection"; 
		// [utilizzato sia per utente nella sessione che per gli
		// altri utenti]
	}

	@RequestMapping("taggedPhoto")
	public String getTaggedPhoto(HttpSession session, Model model, @RequestParam("username") String username,@RequestParam("lastIndex") int lastIndex) {
		User user = userService.getUser(username);
		List<PostPreviewDTO> postOfUser = profileService.getPostTaggedOfUser(user.getUsername(),lastIndex);
		model.addAttribute("posts", postOfUser);
		return "fragment/userProfileFragment/taggedPhotoSection"; // Per aggiungere solo i post in cui e' taggato
		// l'utente [utilizzato sia per utente nella
		// sessione che per gli altri utenti]
	}

	@RequestMapping("bookmarkPhoto")
	public String getBookmarkPhoto(HttpSession session, Model model, @RequestParam("lastIndex") int lastIndex) {
		User user = (User) session.getAttribute("user");
		List<PostPreviewDTO> postOfUser = profileService.getBookmarkOfUser(user.getUsername(),lastIndex);
		model.addAttribute("posts", postOfUser);
		return "fragment/userProfileFragment/bookmarkPhotoSection";
	}
}