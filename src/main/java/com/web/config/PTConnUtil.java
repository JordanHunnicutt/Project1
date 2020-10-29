package com.web.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PTConnUtil implements ConnectionInterface{

	private static PTConnUtil instance;
	
	private String url = "jdbc:postgresql://db-hunni-1.cbv4smezwjgv.us-east-2.rds.amazonaws.com:5432/hunni_db?currentSchema=p_1_schema";
	private String username = "p_0_user";
	private String password = "projectzero";
	
	@Override
	public ConnectionInterface getInstance() {
		if(instance == null) {
			instance = new PTConnUtil();
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
