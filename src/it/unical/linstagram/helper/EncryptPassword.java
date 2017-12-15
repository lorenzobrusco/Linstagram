package it.unical.linstagram.helper;

import java.security.MessageDigest;

//	http://www.rgagnon.com/javadetails/java-0400.html

public class EncryptPassword {
     
	public static String encrypt(String password) {
		String hash = null;
		
		try {
			hash = byteArrayToHexString(EncryptPassword.computeHash(password));
//			System.out.println("the computed hash (hex string) : " + hash);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return hash;
	}
	
	public static String checkPassword(String newPassword, String savedPassword) {
		String hash;
		try {
			hash = byteArrayToHexString(EncryptPassword.computeHash(newPassword));
//			System.out.println(hash);

			if (hash.equals(savedPassword)) 
				return hash;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static byte[] computeHash(String x) throws Exception {
		MessageDigest d =null;
	    d = MessageDigest.getInstance("SHA-1");
	    d.reset();
	    d.update(x.getBytes());
	    return  d.digest();
	}

	private static String byteArrayToHexString(byte[] b) {
		 
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