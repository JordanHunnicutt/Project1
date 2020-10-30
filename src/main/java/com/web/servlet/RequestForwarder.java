package com.web.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.config.SessionController;
import com.web.controller.UserController;
import com.web.models.Reimbursement;

public class RequestForwarder {

	private static SessionController sc = new SessionController();
	
	public String traffic(HttpServletRequest req, HttpServletResponse resp){
		switch(req.getRequestURI()) {
		case "/Project1-1.0.0/html/home.page":
			//drop -1.0.0 for local host
			//System.out.println(req.getParameter("pwText"));
			return new UserController().loginUserController(req.getParameter("userText"), req.getParameter("pwText"), req);
		case "/Project1-1.0.0/html/table.page":
			sc.setSessionTable(req);
			return "reimbTable.html";
//		case "/Project1-1.0.0/html/guide.page":
//			return UserController().guidelineCheck(req);
		case "/Project1-1.0.0/html/add.page":
			return "addReimb.html";
		case "/Project1-1.0.0/html/edit.page":
			return "editReimb.html";
		case "/Project1-1.0.0/html/logout.page":
			sc.invalidate(req);
			return "index.html";
		default:
			return "index.html";
		}
	}
	
	public void info(HttpServletRequest req, HttpServletResponse resp) {
		switch(req.getRequestURI()) {
		case "/Project1-1.0.0/html/table.json":
			UserController uc = new UserController();
			uc.userReimbursementController(req, resp);
			return;
		default:
			Reimbursement r = new Reimbursement();
			r.setDescription("Didn't get the right URI");
			try {
				resp.getWriter().println(new ObjectMapper().writeValueAsString(r));
			} catch (IOException e) {
			
			}
			return;
		}
	}
	
}
