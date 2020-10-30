package com.web.repository;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class UserDaoTest {

	private static final UserDao ud = new UserDao();
	
	@Test
	public void findAllTest() {
		assertNotNull(ud.findAll());
	}
	
	@Test
	public void findByIdTest() {
		assertNotNull(ud.findById(1));
	}
	
	@Test
	public void findByNameTest() {
		assertNotNull(ud.findByName("emp1"));
	}
	
	@Test
	public void findByNameAndPassTest() {
		assertNotNull(ud.findByNameAndPass("emp1", "empPass123"));
	}
	
	@Test
	public void findRolesTest() {
		assertNotNull(ud.findAllRoles());
	}
	
	@Test
	public void createUserTest() {
		assertNotNull(ud.create(null));
	}
	
	@Test
	public void updateUserTest() {
		assertNotNull(ud.update(null));
	}
	
	@Test
	public void deleteUserTest() {
		assertNotNull(ud.delete(null));
	}
	
}
