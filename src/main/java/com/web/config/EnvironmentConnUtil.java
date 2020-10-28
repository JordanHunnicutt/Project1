package com.web.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EnvironmentConnUtil implements ConnectionInterface{
	
	private static EnvironmentConnUtil instance;
	
	private String url = System.getenv("url1");;
	private String username = System.getenv("username");;
	private String password = System.getenv("password");;
	
	@Override
	public ConnectionInterface getInstance() {
		if(instance == null) {
			instance = new EnvironmentConnUtil();
		}
		return instance;
	}

	@Override
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

	{ //because we're creating our own connection, we need to make sure we have our own drivers, instead of having tomcat manage it for us
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
