package com.example.fitnessshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.fitnessshop.bean.User;

public class UserDao {
	
	private final Logger LOG = Logger.getLogger(UserDao.class.getName());
	
	/**
	 * Verify weather user exists after authentication.
	 * 
	 * @param connection connection to the database
	 * @param user user to verify
	 * @return boolean
	 */
	public boolean validateUser(Connection connection, User user) {
		boolean isValidUser = false;
		
		try {
			// write SQL query
			String sql = "SELECT * FROM users WHERE username = ? AND password = ?;";
			
			// get preparedStatement
			PreparedStatement statement = connection.prepareStatement(sql);
			
			// set SQL parameters
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			
			// execute the query
			ResultSet set = statement.executeQuery();
			while(set.next()) {
				isValidUser= true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.log(Level.SEVERE, e, null);
		}
		return isValidUser;
	}
	
	/**
	 * check if a username is available in database
	 * @param connection connection to the database
	 * @param username the username to check
	 * @return boolean
	 */
	public boolean isUsernameAvailable(Connection connection, String username) {
		boolean isAvailable = true;
		
		try {
			// write SQL query
			String sql = "SELECT * FROM users WHERE username = ?;";
			
			// get preparedStatement
			PreparedStatement statement = connection.prepareStatement(sql);
			
			// set SQL parameters
			statement.setString(1, username);
			
			// execute the query
			ResultSet set = statement.executeQuery();
			while(set.next()) {
				isAvailable= false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.log(Level.SEVERE, e, null);
		}
		return isAvailable;
	}
	
	/**
	 * Create a new user.
	 * 
	 * @param connection connection to the database
	 * @param username 
	 * @param password
	 * @return persisted user
	 */
	public User registerUser(Connection connection, User user ) {
		try {
			
			// write the SQL insert
			String sql = "INSERT INTO users(username, password) VALUES(?,?);";
			// get preparedStatement
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		    // set SQL parameters
			statement.setString(1,  user.getUsername());
			statement.setString(2, user.getPassword());
			
			// execute the query
			int rows = statement.executeUpdate();
			
			// check if user entity is created
			if (rows == 0) {
	            throw new SQLException("Failled to create user, no rows affected.");
	        }
			
			// check weather userID has been generated
	        ResultSet generatedIds = statement.getGeneratedKeys();
            if (generatedIds.next()) {
                user.setId(generatedIds.getInt(1));
            }
            else {
                throw new SQLException("Failled to create user, no userID obtained.");
            }
	        
	
			
		} catch (SQLException e) {
			System.out.println("ERROR CREATION OF USER TO DATABASE");
			 e.printStackTrace();
			// LOG.log(Level.SEVERE, e, null);
		}
		
		return user;
	}
}
