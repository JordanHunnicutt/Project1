package com.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.config.SessionController;
import com.web.models.Reimbursement;
import com.web.models.User;
import com.web.service.ReimbursementService;
import com.web.service.UserService;

public class UserController {

	private static final UserService us = new UserService();
	private static final ReimbursementService rs = new ReimbursementService();
	private static final Logger logger = LogManager.getLogger(UserController.class);
	private static SessionController sc = new SessionController();
	
	//return the url to send to, also add user to session
	//need to make a test for this
	public String loginUserController(String name, String password, HttpServletRequest req) {
		String retStr = "";
		User u = us.loginService(name, password);
		
		try {
			boolean b = u.equals(null);
			
			new SessionController().setSessionUser(req, u);
			
			if(u.getRoleId()==1) {
				return "empHomePage.html";
			} else if(u.getRoleId()==2) {
				return "fmHomePage.html";
			} else {
				return "index.html";
			}
			
		} catch (NullPointerException e) {
			logger.info("Controller failed to log in, no user found" + e);
			return "index.html";
		}
		
	}
	
	public void userReimbursementController(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("type/json");
		switch(req.getParameter("tableType")) {
			case "allPending":
				//give all of the pending requests
				List<Reimbursement> reimbursements = rs.getPendingReimbursements();
				try {
					res.getWriter().println(new ObjectMapper().writeValueAsString(reimbursements));
				} catch (IOException e){
					
				}
				return;
			case "user":
				//give a single user's requests
				User u = sc.getSessionUser(req);
				List<Reimbursement> reimbursements2 = us.userReimbursementService(u.getUserId());
				
				try {
					res.getWriter().println(new ObjectMapper().writeValueAsString(reimbursements2));
				} catch (IOException e){
					
				}
				return;
			case "every":
				//give all of the requests
				List<Reimbursement> reimbursements3 = rs.getAllReimbursements();
				try {
					res.getWriter().println(new ObjectMapper().writeValueAsString(reimbursements3));
				} catch (IOException e){
					
				}
				return;
			default:
				logger.info("No request parameter found");
				Reimbursement r = new Reimbursement();
				r.setDescription("Didn't get a parameter");
				try {
					res.getWriter().println(new ObjectMapper().writeValueAsString(r));
				} catch (IOException e) {
				
				}
				return;
		}
		
		
		
		

	}
	
}
