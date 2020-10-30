package com.web.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.config.SessionController;
import com.web.models.Reimbursement;
import com.web.models.ReimbursementStatus;
import com.web.models.ReimbursementType;
import com.web.service.ReimbursementService;

public class ReimbursementController {

	private static final ReimbursementService rs = new ReimbursementService();
	private static SessionController sc = new SessionController();
	private static final Logger logger = LogManager.getLogger(ReimbursementController.class);
	
	public void getTypesController(HttpServletResponse res) {
		res.setContentType("text/json");
		
		List<ReimbursementType> types = rs.getTypesService();
		
		try {
			res.getWriter().println(new ObjectMapper().writeValueAsString(types));
		} catch (IOException e) {
			
		}
		
	}
	
	public void getStatusesController(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/json");
		
		List<ReimbursementStatus> statuses = rs.getStatusesService(req);
		
		try {
			res.getWriter().println(new ObjectMapper().writeValueAsString(statuses));
		} catch (IOException e) {
			
		}
	}
	
	public void getReimbursementIdControllerE(HttpServletRequest req) {
		int i = Integer.parseInt(req.getParameter("reimbSel"));
		
		Reimbursement r = rs.getReimbursementIdService(i);
		sc.setSessionReimbursement(req, r);
	}
	
	public void updateController(HttpServletRequest req) {
		int i = sc.getSessionReimbursement(req).getReimbursementId();
		Reimbursement r = rs.getReimbursementIdService(i);
		
		switch(req.getParameter("reimbType")) {
		case "Lodging":
			r.setTypeId(1);
			break;
		case "Travel":
			r.setTypeId(2);
			break;
		case "Food":
			r.setTypeId(3);
			break;
		case "Other":
			r.setTypeId(4);
			break;
		}
		
		r.setAmount(Double.parseDouble(req.getParameter("amountText")));
		r.setDescription(req.getParameter("descText"));
		
		switch(req.getParameter("rStatus")) {
		case "submitted":
			r.setStatusId(1);
			break;
		case "approved":
			r.setStatusId(2);
			break;
		case "rejected":
			r.setStatusId(3);
			break;
		}
		
		r.setResolverId(sc.getSessionUser(req).getUserId());
		
		int updated = rs.editReimbursementService(r);
		
		if(updated == 0) {
			logger.info("Failed to update reimbursement");
		}
		
		
	}
	
	public void insertController(HttpServletRequest req) {
		Reimbursement r = new Reimbursement();
		
		switch(req.getParameter("reimbType")) {
		case "Lodging":
			r.setTypeId(1);
			break;
		case "Travel":
			r.setTypeId(2);
			break;
		case "Food":
			r.setTypeId(3);
			break;
		case "Other":
			r.setTypeId(4);
			break;
		}
		
		r.setAmount(Double.parseDouble(req.getParameter("amountText")));
		r.setDescription(req.getParameter("descText"));
		r.setResolverId(1);
		r.setTypeId(1);
		r.setAuthorId(sc.getSessionUser(req).getUserId());
		r.setSubmitDate(Timestamp.valueOf(LocalDateTime.now()));
		
		int added = rs.addReimbursementService(r);
		
		if(added == 0) {
			logger.info("Failed to add reimbursement");
		}
		
	}
	
}
