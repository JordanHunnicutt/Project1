package com.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.config.SessionController;
import com.web.models.Reimbursement;
import com.web.models.ReimbursementBuilt;
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
		User u = us.loginService(name, password);
		
		try {
			
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
	
	public boolean userReimbursementController(HttpServletRequest req, HttpServletResponse res) {
		
		String type = "";
		
		try {
			res.setContentType("text/json");
			type = sc.getSessionTable(req);
		} catch(NullPointerException e) {
			type = "";
		}
		
		
		
		switch(type) {
			case "allPending":
				//give all of the pending requests
				List<Reimbursement> reimbursements = rs.getPendingReimbursements();
				List<ReimbursementBuilt> reimbursementsBuilt = new ArrayList<>();
				for(Reimbursement r : reimbursements) {
					reimbursementsBuilt.add(new ReimbursementBuilt(r));
				}				
				try {
					res.getWriter().println(new ObjectMapper().writeValueAsString(reimbursementsBuilt));
				} catch (IOException e){
					return false;
				}
				return true;
			case "user":
				//give a single user's requests
				User u = sc.getSessionUser(req);
				List<Reimbursement> reimbursements2 = us.userReimbursementService(u.getUserId());
				List<ReimbursementBuilt> reimbursementsBuilt2 = new ArrayList<>();
				for(Reimbursement r : reimbursements2) {
					reimbursementsBuilt2.add(new ReimbursementBuilt(r));
				}
				try {
					res.getWriter().println(new ObjectMapper().writeValueAsString(reimbursementsBuilt2));
				} catch (IOException e){
					return false;
				}
				return true;
			case "every":
				//give all of the requests
				List<Reimbursement> reimbursements3 = rs.getAllReimbursements();
				List<ReimbursementBuilt> reimbursementsBuilt3 = new ArrayList<>();
				for(Reimbursement r : reimbursements3) {
					reimbursementsBuilt3.add(new ReimbursementBuilt(r));
				}				
				try {
					res.getWriter().println(new ObjectMapper().writeValueAsString(reimbursementsBuilt3));
				} catch (IOException e){
					return false;
				}
				return true;
			default:
				logger.info("No request parameter found");
				Reimbursement r = new Reimbursement();
				r.setDescription("Didn't get a parameter");
				try {
					res.getWriter().println(new ObjectMapper().writeValueAsString(r));
				} catch (IOException e) {
					return false;
				} catch (NullPointerException e) {
					return false;
				}
				return true;
		}
		
		
		
		

	}
	
	public String backToHome(HttpServletRequest req) {
		if(sc.getSessionUser(req).getRoleId()==1) {
			return "empHomePage.html";
		} else if (sc.getSessionUser(req).getRoleId()==2) {
			return "fmHomePage.html";
		} else {
			return "index.html";
		}
	}
	
}
