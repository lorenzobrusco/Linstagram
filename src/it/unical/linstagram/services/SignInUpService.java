package it.unical.linstagram.services;

import org.springframework.stereotype.Service;

@Service
public class SignInUpService {

	public MessageCode signInAttempt(String username, String password) {
		
		//TODO implement sign in check
		
		return MessageCode.SUCCESS_SIGN_IN;
	}

	/**
	 * It checks whether the user is already exists or there is some type of errors 
	 * and return a messagecode
	 * @param email
	 * @param username
	 * @param password
	 * @return
	 */
	public MessageCode signUpAttempt(String email, String username, String password) {
		//TODO implement sign in check
		return MessageCode.SUCCESS_SIGN_UP;
	}

	
}
