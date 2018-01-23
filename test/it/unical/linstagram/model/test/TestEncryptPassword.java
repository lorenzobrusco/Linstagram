package it.unical.linstagram.model.test;

import org.junit.Assert;
import org.junit.Test;

import it.unical.linstagram.config.CustomPasswordEncoder;

public class TestEncryptPassword {

	private CustomPasswordEncoder passwordEncoder = new CustomPasswordEncoder();
	
	@Test
	public void testPassword() {
//		1E4E888AC66F8DD41E00C5A7AC36A32A9950D271
		
		String pass1 = passwordEncoder.encode("ciao");
		String pass2 = "ciao";
		Assert.assertEquals(true, passwordEncoder.matches(pass2, pass1));	
	}
		
	
}
