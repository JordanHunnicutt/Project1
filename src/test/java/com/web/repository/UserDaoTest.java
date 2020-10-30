package com.web.repository;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.web.models.User;

public class UserDaoTest {

	private static final UserDao ud = new UserDao();
	private User u = new User();
	
	@Before
	public void setup() {
		u.setUsername("test");
		u.setPassword("testPass");
		u.setRoleId(2);
	}
	
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
		assertNotNull(ud.create(u));
	}
	
	@Test
	public void updateUserTest() {
		User u = ud.findById(2);
		
		assertNotNull(ud.update(u));
	}
	
	@Test
	public void deleteUserTest() {
		u.setUserId(ud.findByName(u.getUsername()).getUserId());
		assertNotNull(ud.delete(u.getUserId()));
	}
	
}
