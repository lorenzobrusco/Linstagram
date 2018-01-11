package it.unical.linstagram.services;

import java.util.Random;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.linstagram.helper.EncryptPassword;
import it.unical.linstagram.helper.MessageResponse;
import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.ModelDAO;
import it.unical.linstagram.persistence.UserDAO;

@Service
public class SignInUpService {

	@Autowired
	private UserDAO userDao;
	@Autowired
	private ModelDAO modelDao;
	
	@Autowired
	private MailService mailService;
	
	/**
	 * Try signin.
	 * 
	 * @param access (email or username)
	 * @param password
	 * @return
	 */
	public MessageResponse signInAttempt(String access, String password) {
		EmailValidator ev = EmailValidator.getInstance();
		User user = null;
		if (ev.isValid(access)) {
			//TODO: prova a loggare con email
			String savedPassword = userDao.getPasswordByEmail(access);
			String passEncrypted = EncryptPassword.checkPassword(password, savedPassword);
			
			if (passEncrypted != null) {
				user = userDao.getUserByEmailAndPass(access, passEncrypted);
			}
			else
				user = null;
		} else {
			String savedPassword = userDao.getPasswordByUsername(access);
			String passEncrypted = EncryptPassword.checkPassword(password, savedPassword);
//			System.out.println("saved:"+savedPassword);
			if (passEncrypted != null) {
				user = userDao.getUserByUsernameAndPass(access, passEncrypted);
			}
			else
				user = null;
		}
		if (user == null)
			return new MessageResponse(MessageCode.ERROR_SIGN_IN, null, "");
		return new MessageResponse(MessageCode.SUCCESS_SIGN_IN, user, "");
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
		User user1 = userDao.getUserByEmail(email);
		if (user1 != null)
			return MessageCode.ERROR_EMAIL_ALREADY_USED;

		User user2 = userDao.getUserByUsername(username);
		if (user2 != null)
			return MessageCode.ERROR_USERNAME_ALREADY_USED;

		String passEncrypted = EncryptPassword.encrypt(password);
		
		User newUser = new User(username, email, passEncrypted);
		modelDao.save(newUser);

		return MessageCode.SUCCESS_SIGN_UP;
	}
	
	public MessageResponse existUser(String username,String email) {
		User userByUsernameAndEmail = userDao.getUserByUsernameAndEmail(username,email);
		if(userByUsernameAndEmail != null)
			return new MessageResponse(MessageCode.OK,userByUsernameAndEmail, null);
		return new MessageResponse(MessageCode.USER_NOT_EXIST,null, null);
	}
	
	public void setNewRandomPassword(User user) {
		String generatedPass = generateRandomPassword();
		String passEncrypted = EncryptPassword.encrypt(generatedPass);
		user.setPassword(passEncrypted);
		modelDao.update(user);
		mailService.sendmail(user.getEmail(),user.getUsername(),generatedPass);
	}
	
	private String generateRandomPassword() {
		final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		final int passlength = 8;
		StringBuffer randStr = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < passlength; i++) {
			int randIndex = random.nextInt(CHAR_LIST.length());
			randStr.append(CHAR_LIST.charAt(randIndex));
		}
		return randStr.toString();
	}
	

}
