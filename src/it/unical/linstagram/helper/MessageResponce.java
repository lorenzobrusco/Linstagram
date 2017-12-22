package it.unical.linstagram.helper;

import it.unical.linstagram.services.MessageCode;

public class MessageResponce {
	
	private MessageCode messageCode;
	private Object obj;
	private String message;
	
	public MessageResponce(MessageCode messageCode, Object obj, String message) {
		super();
		this.messageCode = messageCode;
		this.obj = obj;
		this.message = message;
	}
	public MessageCode getMessageCode() {
		return messageCode;
	}
	public Object getObj() {
		return obj;
	}

	public String getMessage() {
		return message;
	}
	
}
