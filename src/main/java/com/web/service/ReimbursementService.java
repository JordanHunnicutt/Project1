package com.web.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.web.models.Reimbursement;
import com.web.repository.ReimbursementDao;

public class ReimbursementService {

	private static final ReimbursementDao rd = new ReimbursementDao();
	private static final Logger logger = LogManager.getLogger(ReimbursementService.class);
	
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
	
}
