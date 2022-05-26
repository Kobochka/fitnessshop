package com.example.fitnessshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.fitnessshop.bean.Payment;

public class PaymentDao {
	
	private final Logger LOG = Logger.getLogger(PaymentDao.class.getName());
	
	
	/**
	 * Create a new payment.
	 * 
	 * @param connection connection to the database
	 * @param payment payment to save
	 * @return persisted payment
	 */
	public Payment createPayment(Connection connection, Payment payment) {
		try {
			
			// write the SQL insert
			String sql = "INSERT INTO payments(paymentCardNumber, paymentCardCV,"
								+ " paymentCardExpired) "
								+ "	VALUES(?,?,?);";
			// get preparedStatement
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		    // set SQL parameters
			statement.setString(1,  payment.getCardNumber());
			statement.setString(2, payment.getCardCVCode());
			statement.setString(3, payment.getCardExpiration());
			
			// execute the query
			int rows = statement.executeUpdate();
			
			// check if payment entity is created
			if (rows == 0) {
	            throw new SQLException("Failled to create payment, no rows affected.");
	        }
			
			// check weather paymentID has been generated
	        ResultSet generatedIds = statement.getGeneratedKeys();
            if (generatedIds.next()) {
            	payment.setId(generatedIds.getInt(1));
            }
            else {
                throw new SQLException("Failled to create payment, no paymentID obtained.");
            }
	        
	
			
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, e, null);
		}
		
		return payment;
	}
	
}
