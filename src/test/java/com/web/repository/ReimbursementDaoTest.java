package com.web.repository;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.web.models.Reimbursement;

public class ReimbursementDaoTest {

	private static final ReimbursementDao rd = new ReimbursementDao();
	
	@Test
	public void findAllRTest() {
		assertNotNull(rd.findAll());
	}
	
	@Test
	public void findAllTTest() {
		assertNotNull(rd.findAllTypes());
	}

	@Test
	public void findAllSTest() {
		assertNotNull(rd.findAllStatuses());
	}
	
	@Test
	public void findByRIdTest() {
		assertNotNull(rd.findById(2));
	}
	
	@Test
	public void findByAuthTest() {
		assertNotNull(rd.findByAuthor(2));
	}
	
	@Test
	public void findByPendingTest() {
		assertNotNull(rd.findByPending());
	}
	
	@Test
	public void findTypeByIdTest() {
		assertNotNull(rd.findStatusById(1));
	}
	
	@Test
	public void findStatusByIdTest() {
		assertNotNull(rd.findTypeById(1));
	}
	
	@Test
	public void createRTest() {
		assertNotNull(rd.create(null));
	}
	
	@Test
	public void updateRTest() {
		Reimbursement r = rd.findById(5);
		
		assertNotNull(rd.update(r));
	}
	
	@Test
	public void deleteRTest() {
		assertNotNull(rd.delete(null));
	}
	
	@Test
	public void createTTest() {
		assertNotNull(rd.createType("funType"));
	}
}
