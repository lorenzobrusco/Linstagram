package it.unical.linstagram.helper;

import javax.servlet.http.HttpSession;

import it.unical.linstagram.model.User;

public class UserManager {
	
	
	public static boolean checkLogged(HttpSession session) {
		if(session.getAttribute("user") != null)
			return true;
		return false;
	}

	/**
	 * @param id: searched user by id  
	 * @param me: logged user
	 * @return
	 */
	public User loadUser(int id, User me) {
		return null;
	}
	
	/**
	 * @param user: searched user  
	 * @param me: logged user
	 * @return
	 */
	public User loadUser(User user, User me) {
		return null;
	}
	
	
	

}
