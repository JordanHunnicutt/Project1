package com.web.repository;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.web.models.Reimbursement;

public class ReimbursementDaoTest {

	private static final ReimbursementDao rd = new ReimbursementDao();
	private Reimbursement r = new Reimbursement();
	
	@Before
	public void setup() {
		r.setAuthorId(2);
		r.setResolverId(1);
		r.setTypeId(1);
		r.setStatusId(1);
	}
	
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
		assertNotNull(rd.create(r));
	}
	
	@Test
	public void updateRTest() {
		Reimbursement r = rd.findById(5);
		
		assertNotNull(rd.update(r));
	}
	
	@Test
	public void deleteRTest() {
		List<Reimbursement> rs = rd.findAll();
		int highest = 0;
		for(Reimbursement reimb : rs) {
			if(reimb.getReimbursementId() > highest) {
				highest = reimb.getReimbursementId();
			}
		}
		
		assertNotNull(rd.delete(highest));
	}
	
	@Test
	public void createTTest() {
		assertNotNull(rd.createType("funType"));
	}
}
