package com.userAdmin.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.userAdmin.beans.UserBean;
import com.userAdmin.dao.hibernate.interfaces.UserAdministration.TestDAO;
import com.userAdmin.dao.model.UserAdministration.UserDetails;
import com.userAdmin.dao.model.UserAdministration.VerificationToken;
import com.userAdmin.services.TestService;

@RestController
@RequestMapping("/userProfile")
public class UserManagement {
	
	@Autowired
	private TestService testService;
	
	@RequestMapping(value="/create",method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public String createUserProfile(@RequestBody UserBean userBean) {
		System.out.println("createUserProfile"+userBean.toString());
		//testService.createUserProfile(userBean);
		return "Hai";
	}
	
	@RequestMapping(value = "/getUserProfile",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public UserBean getUserProfile() {
		UserBean userBean = new UserBean();
		userBean.setEmailId("ajsHJsfgh");
		userBean.setPassword("Aas");
		userBean.setUserName("jashkjsa");
		return userBean;
	}
}