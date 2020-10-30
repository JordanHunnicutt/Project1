package com.web.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.controller.UserController;

public class RequestForwarder {

	public String traffic(HttpServletRequest req, HttpServletResponse resp){
		switch(req.getRequestURI()) {
		case "/Project1-1.0.0/html/home.page":
			//drop -1.0.0 for local host
			//System.out.println(req.getParameter("pwText"));
			return new UserController().loginUserController(req.getParameter("userText"), req.getParameter("pwText"), req);
		case "/Project1-1.0.0/html/table.page":
			return "reimbTable.html";
//		case "/Project1-1.0.0/html/guide.page":
//			return UserController().guidelineCheck(req);
		case "/Project1-1.0.0/html/add.page":
			return "addReimb.html";
		case "/Project1-1.0.0/html/edit.page":
			return "editReimb.html";
		case "/Project1-1.0.0/html/logout.page":
			return "index.html";
		default:
			return "index.html";
		}
	}
	
	public void info(HttpServletRequest req, HttpServletResponse resp) {
		switch(req.getRequestURI()) {
		case "/Project1-1.0.0/html/table.json":
			return;// new UserController().userReimbursementController(req, resp);
		}
	}
	
}
