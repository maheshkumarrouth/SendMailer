package com.userAdmin.events;

import org.springframework.context.ApplicationEvent;

import com.userAdmin.dao.model.UserAdministration.UserDetails;

public class OnRegistrationCompleteEvent extends ApplicationEvent{

	private static final long serialVersionUID = 1L;
	private UserDetails userDetails;

	public OnRegistrationCompleteEvent(UserDetails userDetails) {
		super(userDetails);
		System.out.println("Debug@ stmt :: OnRegistrationCompleteEvent");
		setUserDetails(userDetails);
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	
}
