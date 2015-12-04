package com.userAdmin.managers;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.userAdmin.beans.UserBean;
import com.userAdmin.dao.hibernate.interfaces.UserAdministration.TestDAO;
import com.userAdmin.dao.model.UserAdministration.UserDetails;
import com.userAdmin.dao.model.UserAdministration.VerificationToken;

@Repository
public class TestManager {
	@Autowired
	private TestDAO testDAO;
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	@Transactional(value="transactionManager")
	public void saveUserProfile(UserBean userBean){
		UserDetails userDetails = new UserDetails();
		userDetails.setEmailId(userBean.getEmailId());
		userDetails.setPassword(userBean.getPassword());
		userDetails.setUserName(userBean.getUserName());
		userDetails.setVerified(new Byte("0"));
		System.out.println("Debug@ stmt :: us"+userDetails.toString());
		testDAO.save(userDetails);
		eventPublisher.publishEvent(new com.userAdmin.events.OnRegistrationCompleteEvent(userDetails));
		System.out.println("Debug @ stmt :: saveUserProfile");
	}
	
	@Transactional
	public VerificationToken generateToken(UserDetails userDetails){
		
		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setUserDetails(userDetails);
		String token = UUID.randomUUID().toString();
		verificationToken.setToken(token);
		verificationToken.setExpireDate(new Date());
		testDAO.save(verificationToken);
		return verificationToken;
		
	}
	
}
