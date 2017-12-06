package it.unical.linstagram.services;

import org.springframework.stereotype.Service;

@Service
public class SignInUpService {

	public MessageCode signInAttempt(String username, String password) {
		
		//TODO implement sign in check
		
		return MessageCode.SUCCESS_SIGN_IN;
	}

	public MessageCode signUpAttempt(String email, String username, String password) {
		
		//TODO implement sign in check
		return MessageCode.SUCCESS_SIGN_UP;
	}

	
}
