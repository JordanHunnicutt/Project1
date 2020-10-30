package com.web.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class UserServiceTest {

	private static final UserService us = new UserService();
	
	@Test
	public void loginServiceTest() {		
		assertNotNull(us.loginService("emp1", "empPass123"));		
	}
	
	@Test
	public void userReimbursementTest() {
		assertNotNull(us.userReimbursementService(2));
	}
	
}
