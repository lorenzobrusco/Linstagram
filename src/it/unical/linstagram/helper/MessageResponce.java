package it.unical.linstagram.helper;

import it.unical.linstagram.services.MessageCode;

public class MessageResponce {
	
	private MessageCode messageCode;
	private Object obj;
	
	public MessageResponce(MessageCode messageCode, Object obj) {
		super();
		this.messageCode = messageCode;
		this.obj = obj;
	}
	public MessageCode getMessageCode() {
		return messageCode;
	}
	public Object getObj() {
		return obj;
	}

}
