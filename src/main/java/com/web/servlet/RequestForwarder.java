package com.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.controller.UserController;

public class RequestForwarder {

	public String traffic(HttpServletRequest req, HttpServletResponse resp){
		switch(req.getRequestURI()) {
		case "/Project1-1.0.0/html/home.page":
			//drop -1.0.0 for local host
			System.out.println(req.getParameter("pwText"));
			return new UserController().loginUserController(req.getParameter("userText"), req.getParameter("pwText"), req);
		default:
			return "index.html";
		}
	}
	
	public void info(HttpServletRequest req, HttpServletResponse resp) {
		
	}
	
}
