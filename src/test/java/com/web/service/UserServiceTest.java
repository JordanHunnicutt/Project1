package com.web.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.web.models.User;
import com.web.repository.UserDao;

public class UserServiceTest {

	private static final UserService us = new UserService();
	
	@Before
	public void setup(){
	}
	
	@Test
	public void loginServiceTest() {		
		assertNotNull(us.loginService("emp1", "empPass123"));		
	}
	
	@Test
	public void userReimbursementTest() {
		assertNotNull(us.userReimbursementService(2));
	}
	
}
