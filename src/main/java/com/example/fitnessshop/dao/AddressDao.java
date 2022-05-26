package com.example.fitnessshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.fitnessshop.bean.Address;

public class AddressDao {

private final Logger LOG = Logger.getLogger(AddressDao.class.getName());
	
	
	/**
	 * Create a new address.
	 * 
	 * @param connection connection to the database
	 * @param customer address to save
	 * @param customerId customer id
	 * @return persisted address
	 */
	public Address registerAddress(Connection connection, Address address, int customerId) {
		try {
			
			// write the SQL insert
			String sql = "INSERT INTO address(addressStreet, addressZipCode, "
								+ "addressCity, addressCountry, customerID, addressStatus) "
								+ "VALUES(?,?,?,?,?,?);";
			
			// get preparedStatement
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		    // set SQL parameters
			statement.setString(1,  address.getStreet());
			statement.setString(2, address.getZipCode());
			statement.setString(3, address.getCity());
			statement.setString(4, address.getCountry());
			statement.setInt(5, customerId);
			statement.setString(6, address.getStatus());
			
			// execute the query
			int rows = statement.executeUpdate();
			
			// check if address entity is created
			if (rows == 0) {
	            throw new SQLException("Failled to create address, no rows affected.");
	        }
			
			// check weather addressId has been generated
	        ResultSet generatedIds = statement.getGeneratedKeys();
            if (generatedIds.next()) {
            	address.setId(generatedIds.getInt(1));
            }
            else {
                throw new SQLException("Failled to create address, no addressID obtained.");
            }
	        
	
		
		} catch (SQLException e) {
			//LOG.log(Level.SEVERE, e, null);
		}
		
		return address;
	}
	
	/**
	 * update an address.
	 * 
	 * @param connection connection to the database
	 * @param customer address to update
	 * @return updated address
	 */
	public Address updateAddress(Connection connection, Address address) {
		try {
			
			// write the SQL update
			String sql = " UPDATE address SET addressStreet = ?, addressZipCode = ?, "
								+ "addressCity = ?, addressCountry = ?, "
								+ "addressStatus = ? ";
			// get preparedStatement
			PreparedStatement statement = connection.prepareStatement(sql);
		    // set SQL parameters
			statement.setString(1,  address.getStreet());
			statement.setString(2, address.getZipCode());
			statement.setString(3, address.getCity());
			statement.setString(4, address.getCountry());
			statement.setString(5, address.getStatus());
			
			// execute the query
			statement.executeUpdate();
			
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, e, null);
		}
		
		return address;
	}
	
	/**
	 * get an address by customerID.
	 * 
	 * @param connection connection to db
	 * @param customerID customer'id
	 * @return the persisted address
	 */
	public Address getAddressByCustomerId(Connection connection, int customerID) {
		Address address = null;
		try {
			// write the sql query
			String sql = "SELECT * FROM address "
							+ "WHERE customerID = ? AND addressStatus = ?;";
			// get preparedStatement
			PreparedStatement statement = connection.prepareStatement(sql);
		    // set SQL parameters
			statement.setInt(1,  customerID);
			statement.setString(2, Address.Status.PRINCIPALE.toString());
			
			// execute the query
			ResultSet set = statement.executeQuery();
			while(set.next()) {
				address = new Address();
				address.setId(set.getInt("addressID"));
				address.setStreet(set.getString("addressStreet"));
				address.setZipCode(set.getString("addressZipCode"));
				address.setCity(set.getString("addressCity"));
				address.setCountry(set.getString("addressCountry"));
				address.setStatus(set.getString("addressStatus"));
			}
		}catch(SQLException ex) {
			LOG.log(Level.SEVERE, ex, null);
		}
		return address;
	}
	
	/**
	 * get an "id" address.
	 * 
	 * @param connection connection to db
	 * @param id address id
	 * @return the persisted address
	 */
	public Address findById(Connection connection, int id) {
		Address address = null;
		try {
			// write the sql query
			String sql = "SELECT * FROM address "
							+ "WHERE addressID = ?;";
			// get preparedStatement
			PreparedStatement statement = connection.prepareStatement(sql);
		    // set SQL parameters
			statement.setInt(1,  id);
			
			// execute the query
			ResultSet set = statement.executeQuery();
			while(set.next()) {
				address = new Address();
				address.setId(set.getInt("addressID"));
				address.setStreet(set.getString("addressStreet"));
				address.setZipCode(set.getString("addressZipCode"));
				address.setCity(set.getString("addressCity"));
				address.setCountry(set.getString("addressCountry"));
				address.setStatus(set.getString("addressStatus"));
			}
		}catch(SQLException ex) {
			LOG.log(Level.SEVERE, ex, null);
		}
		return address;
	}
	

}
