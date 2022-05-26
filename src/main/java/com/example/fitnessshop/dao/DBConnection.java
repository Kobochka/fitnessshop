package com.example.fitnessshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;


public class DBConnection {
	
	private static final Logger LOG = Logger.getLogger("DBConnection.class");
	
	public static Connection getConncetionToDatabase() {
		Connection connection = null;
		
		try {
			
			// load the driver class for mysql
     		Class.forName("com.mysql.jdbc.Driver");
			LOG.info("Mysql Driver registered");
			
			// get hold of the driver manager to establish to connection
			connection = DriverManager.getConnection("jdbc:mysql://node186867-env-9648435.jelastic.regruhosting.ru/fitnessshopdb?autoReconnect=true&useSSL=false", "root", "ABVbhm54844");
			LOG.info("Connection to Mysql database successfully");
			
		} catch (ClassNotFoundException e) {
			LOG.info("Where is your Mysql Driver ?");
			e.printStackTrace();
		} catch (SQLException e) {
			LOG.info("Connection to database fail. checkout console!");
			e.printStackTrace();
		}
		return connection;
	}

}
