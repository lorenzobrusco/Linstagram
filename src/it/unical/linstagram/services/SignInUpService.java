package it.unical.linstagram.services;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.ModelDAO;
import it.unical.linstagram.persistence.UserDAO;

@Service
public class SignInUpService {
	/**
	 * Try signin.
	 * 
	 * @param access (email or username)
	 * @param password
	 * @return
	 */
	public MessageCode signInAttempt(String access, String password) {
		EmailValidator ev = EmailValidator.getInstance();
		User user = null;
		if (ev.isValid(access)) {
			user = UserDAO.getInstance().getUserEmailAndPass(access, password);
		} else {
			user = UserDAO.getInstance().getUserByUsernameAndPass(access, password);
		}
		if (user == null)
			return MessageCode.ERROR_SIGN_IN;
		return MessageCode.SUCCESS_SIGN_IN;
	}

	/**
	 * It checks whether the user is already exists or there is some type of errors
	 * and return a messagecode
	 * 
	 * @param email
	 * @param username
	 * @param password
	 * @return
	 */
	public MessageCode signUpAttempt(String email, String username, String password) {
		UserDAO userDAO = UserDAO.getInstance();
		User user1 = userDAO.getUserByEmail(email);
		if (user1 != null)
			return MessageCode.ERROR_SIGN_UP;

		User user2 = userDAO.getUserByUsername(username);
		if (user2 != null)
			return MessageCode.ERROR_SIGN_UP;

		User newUser = new User(username, email, password);
		ModelDAO.getInstance().save(newUser);

		return MessageCode.SUCCESS_SIGN_UP;
	}

}
