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
	
	public boolean getTypesController(HttpServletResponse res) {
		
		try {
			res.setContentType("text/json");
		} catch(NullPointerException e) {
			//return false;
		}
		
		
		List<ReimbursementType> types = rs.getTypesService();
		
		try {
			res.getWriter().println(new ObjectMapper().writeValueAsString(types));
		} catch (Exception e) {
			return false;
		}
		
		return true;
		
	}
	
	public boolean getStatusesController(HttpServletRequest req, HttpServletResponse res) {
		
		try {
			res.setContentType("text/json");
		} catch (NullPointerException e) {
			return false;
		}
				
		List<ReimbursementStatus> statuses = rs.getStatusesService(req);
		
		try {
			res.getWriter().println(new ObjectMapper().writeValueAsString(statuses));
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	public boolean getReimbursementIdControllerE(HttpServletRequest req) {
		
		int i = 2;
		
		try {
			i = Integer.parseInt(req.getParameter("reimbSel"));
		} catch (NullPointerException e) {
			return false;
		}		
		
		Reimbursement r = rs.getReimbursementIdService(i);
		sc.setSessionReimbursement(req, r);
		return true;
	}
	
	public boolean updateController(HttpServletRequest req) {
		
		int i = 5;
		
		try {
			i = sc.getSessionReimbursement(req).getReimbursementId();
		} catch (NullPointerException e) {
			return false;
		}
		
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
		
		return true;
	}
	
	public boolean insertController(HttpServletRequest req) {
		Reimbursement r = new Reimbursement();
		
		try {
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
		} catch (NullPointerException e){
			return false;
		}
		
		
		r.setAmount(Double.parseDouble(req.getParameter("amountText")));
		r.setDescription(req.getParameter("descText"));
		r.setResolverId(1);
		r.setAuthorId(sc.getSessionUser(req).getUserId());
		r.setSubmitDate(Timestamp.valueOf(LocalDateTime.now()));
		r.setStatusId(1);
		
		if(rs.addReimbursementService(r) == 0) {
			logger.info("Failed to add reimbursement");
		}
		return true;
	}
	
}
