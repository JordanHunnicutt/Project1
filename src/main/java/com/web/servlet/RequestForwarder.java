package com.web.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.config.SessionController;
import com.web.controller.ReimbursementController;
import com.web.controller.UserController;
import com.web.models.Reimbursement;

public class RequestForwarder {

	private static SessionController sc = new SessionController();
	private static final String prep = "/Project1-1.0.0/";
	
	public String traffic(HttpServletRequest req, HttpServletResponse resp){
		switch(req.getRequestURI()) {
		case prep+"html/home.page":
			//drop -1.0.0 for local host
			//System.out.println(req.getParameter("pwText"));
			return new UserController().loginUserController(req.getParameter("userText"), req.getParameter("pwText"), req);
		case prep+"html/table.page":
			sc.setSessionTable(req);
			return "reimbTable.html";
//		case "/Project1-1.0.0/html/guide.page":
//			return UserController().guidelineCheck(req);
		case prep+"html/add.page":			
			return "addReimb.html";
		case prep+"html/edit.page":
			ReimbursementController rc = new ReimbursementController();
			rc.getReimbursementIdControllerE(req);
			return "editReimb.html";
		case prep+"html/update.page":
			ReimbursementController rc2 = new ReimbursementController();
			rc2.updateController(req);
			return new UserController().backToHome(req);
		case prep+"html/insert.page":
			ReimbursementController rc3 = new ReimbursementController();
			rc3.insertController(req);
			return new UserController().backToHome(req);
		case prep+"html/logout.page":
			sc.invalidate(req);
			return "index.html";
		default:
			return "index.html";
		}
	}
	
	public void info(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("in request forwarder");
		switch(req.getRequestURI()) {
		case prep+"table.json":
			System.out.println("in first case");
			UserController uc = new UserController();
			uc.userReimbursementController(req, resp);
			return;
		case prep+"type.json":
			ReimbursementController rc = new ReimbursementController();
			rc.getTypesController(resp);
			return;
		case prep+"status.json":
			ReimbursementController rc2 = new ReimbursementController();
			rc2.getStatusesController(req, resp);
			return;
		default:
			System.out.println("in request forward default");
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
