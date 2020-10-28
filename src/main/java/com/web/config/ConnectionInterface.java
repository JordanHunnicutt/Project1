package com.web.config;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionInterface {
	
	ConnectionInterface getInstance();
	Connection getConnection() throws SQLException;
	
}
