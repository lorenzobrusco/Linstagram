package it.unical.linstagram.config;

import org.springframework.security.crypto.password.PasswordEncoder;

import it.unical.linstagram.helper.EncryptPassword;

public class CustomPasswordEncoder implements PasswordEncoder {
	@Override

	public String encode(CharSequence rawPassword) {
		String hashed = EncryptPassword.encrypt(rawPassword.toString()); 
		return hashed;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return EncryptPassword.checkPassword(rawPassword.toString(), encodedPassword) != null;

	}



}