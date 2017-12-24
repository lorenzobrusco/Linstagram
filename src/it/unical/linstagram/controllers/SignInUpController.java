package it.unical.linstagram.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.unical.linstagram.helper.MessageResponce;
import it.unical.linstagram.model.User;
import it.unical.linstagram.services.MessageCode;
import it.unical.linstagram.services.SignInUpService;

@Controller
public class SignInUpController {

	@Autowired
	private SignInUpService signInService;

	@RequestMapping("/")
	public String getSignInPage() {
		return "signin";
	}

	@RequestMapping(value = "/signUpAttempt", method = RequestMethod.POST)
	public String signUp(@RequestParam String email, @RequestParam String username, @RequestParam String password,HttpSession session) {
		if (signInService.signUpAttempt(email, username, password) == MessageCode.SUCCESS_SIGN_UP) {
			return signIn(username,password,session);
		}
		// TODO: return a string that show the error (already user username/email)
		return "redirect:/index";

	}

	@RequestMapping(value="/signInAttempt",method=RequestMethod.POST)
	public String signIn(@RequestParam String username, @RequestParam String password, HttpSession session) {
		MessageResponce signInAttemptResp = signInService.signInAttempt(username, password);
//		System.out.println(signInAttempt);
		if (signInAttemptResp.getMessageCode() == MessageCode.SUCCESS_SIGN_IN) {
			if(signInAttemptResp.getObj() instanceof User) {
				User user= (User) signInAttemptResp.getObj();
				session.setAttribute("user", user);
				return "redirect:/index";
			}
		}
		// TODO: return a string that show the error -> put it in session and add message in jsp
		return "redirect:/";
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}
