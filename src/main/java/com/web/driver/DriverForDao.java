package com.web.driver;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.web.models.Reimbursement;
import com.web.repository.ReimbursementDao;
import com.web.repository.UserDao;

public class DriverForDao {

	public static void main(String[] args) {
		
		UserDao ud = new UserDao();
		ReimbursementDao rd = new ReimbursementDao();
		
		System.out.println(ud.findByNameAndPass("emp1","empPass123"));
		
	}
	
}
