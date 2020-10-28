package com.web.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.web.models.Reimbursement;
import com.web.models.User;
import com.web.repository.ReimbursementDao;
import com.web.repository.UserDao;

public class UserService {

	private static final UserDao ud = new UserDao();
	private static final ReimbursementDao rd = new ReimbursementDao();
	private static final Logger logger = LogManager.getLogger(UserService.class);
	
	public User loginService(String name, String password) {
		User ret = ud.findByNameAndPass(name, password);

		try {
			boolean b = (ret == null);	
			
		} catch (NullPointerException e){
			logger.info("No user found in login service. "+e);
		}
		
		return ret;
	}
	
	public List<Reimbursement> userReimbursementService(int id) {
		List<Reimbursement> reimbursements = rd.findByAuthor(id);
		
		try {
			boolean b = (reimbursements == null);
		} catch (NullPointerException e) {
			logger.info("No reimbursements found for that user" +e);
		}
		
		return reimbursements;
	}
	
	
}
