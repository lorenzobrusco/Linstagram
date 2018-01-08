package it.unical.linstagram.services;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:adminemail.properties")
public class MailService {

	private JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	private SimpleMailMessage templateMessage;

	@Value("${adminemail.email}")
	private String emailaddr;
	@Value("${adminemail.pass}")
	private String emailpass;

	@PostConstruct
	private void init() {
		mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername(emailaddr);
		mailSender.setPassword(emailpass);

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "false");

		templateMessage=new SimpleMailMessage();
		templateMessage.setSubject("Recover password - Listagram");
	}

	private String buildMailText(String username,String password) {
		String mailText = "Dear "+ username +",\n\n" +
				"This email contains a new password that you can use to access our services.\n" + 
				"The new password is: "+ password +".\n\n" +
				"We advise you to change it as soon as possible.\n\n" + 
				"Best regards, Linstagram.";
		return mailText;
	}


	public void sendmail(String email,String username,String newPassword) {
		// Create a thread safe "copy" of the template message and customize it
		SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
		msg.setTo(email);
		msg.setText(buildMailText(username,newPassword)); 
		try{
			this.mailSender.send(msg);
		}
		catch (MailException ex) {
			System.err.println(ex.getMessage());
		}
	}

}
