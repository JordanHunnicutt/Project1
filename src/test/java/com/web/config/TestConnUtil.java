package com.web.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnUtil implements ConnectionInterface{

	private static TestConnUtil instance;
	
	private String url = System.getenv("url1");;
	private String username = System.getenv("username");;
	private String password = System.getenv("password");;
	
	@Override
	public ConnectionInterface getInstance() {
		if(instance == null) {
			instance = new TestConnUtil();
		}
		return instance;
	}

	@Override
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
	
	
	
}
