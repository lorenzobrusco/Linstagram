package it.unical.linstagram.model.test;

import org.junit.Assert;
import org.junit.Test;

import it.unical.linstagram.config.CustomPasswordEncoder;

public class TestEncryptPassword {

	private CustomPasswordEncoder passwordEncoder = new CustomPasswordEncoder();
	
//	@Test
	public void testPassword() {
//		1E4E888AC66F8DD41E00C5A7AC36A32A9950D271
		
		String pass1 = passwordEncoder.encode("ciao");
		String pass2 = "ciao";
		Assert.assertEquals(true, passwordEncoder.matches(pass2, pass1));	
	}
	
//	@Test
	public void testRegistration() {
		
//		SignInUpService service = new SignInUpService();
//		service.signUpAttempt("eliana@gmail.com", "eliana", "ciao");
////		MessageCode m = service.signUpAttempt("ciao@gmail.com", "eliana", "ciao");
//		
////		assertEquals(MessageCode.ERROR_SIGN_UP, m);
//		
//		UserDAO dao = new UserDAO();
//		System.out.println(dao.getPasswordByUsername("eliana"));
////		System.out.println(dao.getPasswordByEmail("eliana@gmail.com"));
		
	}
	
	
	@Test
	public void testLogin() {
//		
//		SignInUpService service = new SignInUpService();
//		service.signUpAttempt("eliana@gmail.com", "eliana", "ciao");
//		
//		UserDAO dao = new UserDAO();
//		
//		MessageResponse m = service.signInAttempt("eliana@gmail.com", "ciA");
//		
//		assertEquals(MessageCode.ERROR_SIGN_IN, m.getMessageCode());
		
	}
	
}
