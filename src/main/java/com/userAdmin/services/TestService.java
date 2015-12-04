package com.userAdmin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.userAdmin.beans.UserBean;
import com.userAdmin.managers.TestManager;

@Repository
public class TestService {
	
	@Autowired
	private TestManager testManager ; 
	public void createUserProfile(UserBean userBean){
		testManager.saveUserProfile(userBean);
	}
}
