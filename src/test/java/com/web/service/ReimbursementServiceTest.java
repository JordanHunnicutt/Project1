package com.web.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.web.models.Reimbursement;

public class ReimbursementServiceTest {

	private static final ReimbursementService rs = new ReimbursementService();
	
	@Test
	public void editReimbursementTest() {
		Reimbursement r = new Reimbursement();
		r.setDescription("This description was edited.");
		r.setReimbursementId(5);
		r.setAuthorId(2);
		r.setResolverId(1);
		r.setStatusId(1);
		r.setTypeId(1);
		
		assertEquals(1,rs.editReimbursementService(r));
	}
	
	@Test
	public void addReimbursementTest() {
		
		Reimbursement r = new Reimbursement();
		r.setAuthorId(2);
		r.setResolverId(1);
		r.setStatusId(1);
		r.setTypeId(1);
		
		assertEquals(1,rs.addReimbursementService(r));
	}
	
	@Test
	public void getAllReimbursementsTest() {
		assertNotNull(rs.getAllReimbursements());
	}
	
	@Test
	public void getAllPendingTest() {
		assertNotNull(rs.getPendingReimbursements());
	}
	
	@Test
	public void newTypeTest() {
		assertTrue(rs.newReimbType("Foraging"));
	}
	
}
