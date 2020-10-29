package com.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.web.config.SessionController;
import com.web.models.User;
import com.web.service.UserService;

public class UserController {

	private static final UserService us = new UserService();
	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	//return the url to send to, also add user to session
	//need to make a test for this
	public String loginUserController(String name, String password, HttpServletRequest req) {
		String retStr = "";
		User u = us.loginService(name, password);
		
		try {
			boolean b = u.equals(null);
			
			new SessionController().setSessionUser(req, u);
			
			if(u.getRoleId()==1) {
				return "/Project1-1.0.0/html/empHomePage.html";
			} else if(u.getRoleId()==2) {
				return "/Project1-1.0.0/html/fmHomePage.html";
			} else {
				return "/Project1-1.0.0/html/index.html";
			}
			
		} catch (NullPointerException e) {
			logger.info("Controller failed to log in, no user found" + e);
			return "/Project1-1.0.0/html/index.html";
			//drop everything before index.html for localhost
		}
		
	}
	
	
}
