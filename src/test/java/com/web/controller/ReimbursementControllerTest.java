package com.web.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ReimbursementControllerTest {

	private static final ReimbursementController rc = new ReimbursementController();
	
	@Test
	public void getTypesCTest() {
		assertNotNull(rc.getTypesController(null));
	}
	
	@Test
	public void getStatusesCTest() {
		assertNotNull(rc.getStatusesController(null, null));
	}
	
	@Test
	public void getReimbIdCTest() {
		assertNotNull(rc.getReimbursementIdControllerE(null));
	}
	
	@Test
	public void updateCTest() {
		assertNotNull(rc.updateController(null));
	}
	
	@Test
	public void insertCTest() {
		assertNotNull(rc.insertController(null));
	}
}
