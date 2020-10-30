package com.web.service;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.web.config.SessionController;
import com.web.models.Reimbursement;
import com.web.models.ReimbursementStatus;
import com.web.models.ReimbursementType;
import com.web.models.User;
import com.web.repository.ReimbursementDao;

public class ReimbursementService {

	private static final ReimbursementDao rd = new ReimbursementDao();
	private static final Logger logger = LogManager.getLogger(ReimbursementService.class);
	private static SessionController sc = new SessionController();
	
	
	public int addReimbursementService(Reimbursement r) {
		int added = rd.create(r);
		
		if(added == 0) {
			logger.info("No reimbursement was added");
		}
		
		return added;
	}
	
	public int editReimbursementService(Reimbursement r) {
		int edited = rd.update(r);
		
		if(edited == 0) {
			logger.info("No reimbursement was edited");
		}
		
		return edited;
	}
	
	public List<Reimbursement> getAllReimbursements(){
		List<Reimbursement> reimbs = rd.findAll();
		
		try {
			boolean b = (reimbs == null);
		} catch (NullPointerException e) {
			logger.info("No reimbursements were found");
		}
		
		return reimbs;
	}
	
	public List<Reimbursement> getPendingReimbursements(){
		List<Reimbursement> reimbs = rd.findByPending();
		
		try {
			boolean b = (reimbs == null);
		} catch (NullPointerException e) {
			logger.info("No pending reimbursements were found");
		}
		
		return reimbs;
	}
	
	public boolean newReimbType(String type) {
		boolean b = rd.createType(type);
		
		return b;		
	}
	
	public List<ReimbursementType> getTypesService(){
		List<ReimbursementType> types = rd.findAllTypes();
		
		try {
			boolean b = (types == null);
		} catch (NullPointerException e) {
			logger.info("No types found");
		}
		
		return types;
	}
	
	public List<ReimbursementStatus> getStatusesService(HttpServletRequest req){
		
		try {
			User u = sc.getSessionUser(req);
			if(u.getRoleId()==1) {
				ReimbursementStatus rStat = rd.findStatusById(1);
				return Arrays.asList(rStat);
			}
		} catch (NullPointerException e) {
			logger.info("No user found to get statuses by");
		}
		
	
		List<ReimbursementStatus> statuses = rd.findAllStatuses();
		
		try {
			boolean b = (statuses == null);
		} catch (NullPointerException e) {
			logger.info("No statuses found");
		}
		
		return statuses;
	}
	
	public Reimbursement getReimbursementIdService(int i) {
		Reimbursement r = rd.findById(i);
		
		try {
			boolean b = (r == null);
		} catch (NullPointerException e) {
			logger.info("No reimbursement found");
		}
		
		return r;
	}
	
}
