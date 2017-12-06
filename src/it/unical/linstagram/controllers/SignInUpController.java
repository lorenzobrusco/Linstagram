package it.unical.linstagram.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@RequestMapping("/signUpAttempt")
	public String signUp(@RequestParam String email, @RequestParam String username, @RequestParam String password) {
		
		if (signInService.signUpAttempt(email, username, password) == MessageCode.SUCCESS_SIGN_UP) {
			return "redirect:index";
		}
		// TODO: return a string that show the error (already user username/email)
		return "redirect:";

	}

	@RequestMapping("/signInAttempt")
	@ResponseBody
	public String signIn(@RequestParam String username, @RequestParam String password, HttpSession session) {
		if (signInService.signInAttempt(username, password) == MessageCode.SUCCESS_SIGN_IN) {
			session.setAttribute("username", username);
			return "redirect:index";
		}
		// TODO: return a string that show the error (invalid user/email or pass)
		return "redirect:";
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:";
	}

}
