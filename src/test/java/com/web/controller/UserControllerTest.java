package com.web.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class UserControllerTest {

	private static final UserController uc = new UserController();
	
	
	@Test
	public void loginControllerTest() {
		
		assertNotNull(uc.loginUserController(null, null, null));
	}
	
	
	@Test
	public void userControllerTest() {
		assertNotNull(uc.userReimbursementController(null, null));
	}
}
