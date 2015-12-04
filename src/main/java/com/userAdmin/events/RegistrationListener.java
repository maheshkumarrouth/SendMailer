package com.userAdmin.events;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.userAdmin.dao.model.UserAdministration.UserDetails;
import com.userAdmin.dao.model.UserAdministration.VerificationToken;
import com.userAdmin.managers.TestManager;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent>{
	
	@Autowired 
	private TestManager testManager;
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent onRegistrationCompleteEvent) {
		System.out.println("Debug @ stmt :: onApplicationEvent");
		UserDetails userDetails = onRegistrationCompleteEvent.getUserDetails();
		System.out.println("Debug @ stmt :: us"+userDetails);
		VerificationToken verificationToken = testManager.generateToken(onRegistrationCompleteEvent.getUserDetails());
        String confirmationUrl = "http://localhost:8080/SendMailer/regitrationConfirm?token=" + verificationToken.getToken();
        String recipientAddress = userDetails.getEmailId();
        String subject = "Registration Confirmation";
		String message = "http://localhost:8080/SendMailer/regitrationConfirm?token=" + verificationToken.getToken();
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recipientAddress);
		email.setSubject(subject);
		email.setText(message);
		
		// sends the e-mail
		mailSender.send(email);
		System.out.println("Debug @ stmr ;; end of event");
	}

}
