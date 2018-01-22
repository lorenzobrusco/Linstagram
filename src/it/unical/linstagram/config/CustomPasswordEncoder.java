package it.unical.linstagram.config;

import java.security.MessageDigest;

import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {
	@Override

	public String encode(CharSequence rawPassword) {
		String hashed = encrypt(rawPassword.toString()); 
		return hashed;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return checkPassword(rawPassword.toString(), encodedPassword) != null;

	}

	private String encrypt(String password) {
		String hash = null;
		
		try {
			hash = byteArrayToHexString(computeHash(password));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return hash;
	}
	
	private String checkPassword(String newPassword, String savedPassword) {
		String hash;
		try {
			hash = byteArrayToHexString(computeHash(newPassword));
//			System.out.println(hash);
//			System.out.println(savedPassword);
			if (hash.equals(savedPassword)) 
				return hash;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private byte[] computeHash(String x) throws Exception {
		MessageDigest d =null;
	    d = MessageDigest.getInstance("SHA-1");
	    d.reset();
	    d.update(x.getBytes());
	    return  d.digest();
	}

	private String byteArrayToHexString(byte[] b) {
		 
		StringBuffer sb = new StringBuffer(b.length * 2);
	    for (int i = 0; i < b.length; i++) {
	    	int v = b[i] & 0xff;
	    	if (v < 16)
	    		sb.append('0');
	    	 
	    	sb.append(Integer.toHexString(v));
	    }
	    return sb.toString().toUpperCase();
	}
	


}