package it.unical.linstagram.services;

public enum MessageCode {
	
	//Sign_in and Sign_up Error 
	INVALID_USERNAME,
	INVALID_PASSWORD,
	ERROR_EMAIL_ALREADY_USED,
	ERROR_USERNAME_ALREADY_USED,
	
	ERROR_SIGN_IN, //General error
	ERROR_SIGN_UP,

	USER_NOT_EXIST,		//general error for forget password form
	
	SUCCESS_SIGN_IN,
	SUCCESS_SIGN_UP,
	
	USERNAME_FAILED,
	EMAIL_FAILED,
	
	OK,
	
	PASS_WRONG,
	PASS_DIFFERENT,
	
	FOLLOW_FAILED,
	UNFOLLOW_FAILED,

	FAILED,
	
}
