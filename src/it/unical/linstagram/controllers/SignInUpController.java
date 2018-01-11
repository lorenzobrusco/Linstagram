package it.unical.linstagram.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.unical.linstagram.helper.MessageResponse;
import it.unical.linstagram.model.User;
import it.unical.linstagram.services.MediaService;
import it.unical.linstagram.services.MessageCode;
import it.unical.linstagram.services.SignInUpService;
import it.unical.linstagram.services.UserService;

@Controller
public class SignInUpController {

	@Autowired
	private SignInUpService signInService;
	@Autowired
	private MediaService mediaService;

	@Autowired 
	private UserService userService;

	@RequestMapping("/login")
	public String getSignInPage() {
		return "signin";
	}

	@RequestMapping(value = "/forgotPasswordPage", method=RequestMethod.POST)
	public String getForgotPasswordPage() {
		return "fragment/forgotPassword";
	}

	@ResponseBody
	@RequestMapping(value = "/forgotPassword", method=RequestMethod.POST)
	public String forgotPassword(@RequestParam String username,@RequestParam String email) {
		MessageResponse existUser = signInService.existUser(username, email);
		if(existUser.getMessageCode() == MessageCode.OK) {
			if(existUser.getObj() instanceof User) {
				signInService.setNewRandomPassword((User) existUser.getObj());
				return MessageCode.OK.toString();
			}
		}
		return MessageCode.USER_NOT_EXIST.toString();

	}

	@RequestMapping(value = "/signUpAttempt", method = RequestMethod.POST)
	@ResponseBody
	public String signUp(@RequestParam String email, @RequestParam String username, @RequestParam String password) {
		MessageCode signUpAttempt = signInService.signUpAttempt(email, username, password);
		mediaService.createImageDefault();
		return signUpAttempt.toString();
	}

	//	@RequestMapping(value="/signInAttempt",method=RequestMethod.POST)
	//	public String signIn(@RequestParam String username, @RequestParam String password, HttpSession session) {
	//		MessageResponse signInAttemptResp = signInService.signInAttempt(username, password);
	//		//		System.out.println(signInAttempt);
	//		if (signInAttemptResp.getMessageCode() == MessageCode.SUCCESS_SIGN_IN) {
	//			if(signInAttemptResp.getObj() instanceof User) {
	//				User user= (User) signInAttemptResp.getObj();
	//				session.setAttribute("user", user);
	//				mediaService.createImageDefault();
	//				System.out.println("compa marcu");
	//				return "redirect:/index";
	//			}
	//		}
	//		return "redirect:/";
	//	}
	@RequestMapping(value="/setUserSession")
	public String signIn(HttpSession session, Principal principal) {
		User user = userService.getUser(principal.getName());
		System.out.println("CIOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
		session.setAttribute("user", user);
		return "redirect:/";
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}
