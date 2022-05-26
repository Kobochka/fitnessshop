package com.example.fitnessshop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.fitnessshop.bean.Order;

public class OrderDao {
	
	private final Logger LOG = Logger.getLogger(OrderDao.class.getName());
	
	/**
	 * Create a new order.
	 * 
	 * @param connection connection to the database
	 * @param order order to save
	 * @return persisted order
	 */
	public Order createOrder(Connection connection, Order order) {
		try {
			
			// write the SQL insert
			String sql = "INSERT INTO orders(addressID, paymentID,"
								+ " orderDate) "
								+ "	VALUES(?,?,?);";
			
			// get preparedStatement
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		    // set SQL parameters
			statement.setInt(1,  order.getAddressId());
			statement.setInt(2, order.getPaymentId());
			statement.setDate(3, new Date(order.getDate().getTime()));
			
			// execute the query
			int rows = statement.executeUpdate();
			
			// check if order entity is created
			if (rows == 0) {
	            throw new SQLException("Failled to create order, no rows affected.");
	        }
			
			// check weather paymentID has been generated
	        ResultSet generatedIds = statement.getGeneratedKeys();
            if (generatedIds.next()) {
            	order.setId(generatedIds.getInt(1));
            }
            else {
                throw new SQLException("Failled to create order, no orderID obtained.");
            }
	        
	
			
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, e, null);
		}
		
		return order;
	}
	
	/**
	 * find all Orders.
	 * 
	 * @param connection connection to the database
	 * @return List of orders
	 */
	public List<Order> findOrderByUsername(Connection connection, String username){
		
		List<Order> result = new ArrayList<>();
		LOG.info("Request to get all Orders");
		
		try {
			
			// write the select sql
			String sql = "SELECT orders.orderID, SUM(orderItemQuantity*productPrice) AS price, orders.addressID, paymentID, orderDate "
							+ "FROM users "
								+ "INNER JOIN customers "
									+ "ON users.userID = customers.userID "
								+ "INNER JOIN address "
									+ "ON customers.customerID = address.customerID "
								+ "INNER JOIN orders "
									+ "ON address.addressID = orders.addressID "
								+ "INNER JOIN orderItems "
									+ "ON orders.orderID = orderItems.orderID " 
								+ "INNER JOIN products " 
									+ "ON orderItems.productID = products.productID "
								+ "WHERE username =? "
								+ "GROUP BY orders.orderID;";
			
			// get the prepare statement
			PreparedStatement statement = connection.prepareStatement(sql);
			
			// set query parameters
			statement.setString(1, username);
			
			// execute the sql request
			ResultSet set = statement.executeQuery();
			while(set.next()) {
				Order order = new Order();
				order.setId(set.getInt("orderID"));
				order.setAddressId(set.getInt("addressID"));
				order.setPaymentId(set.getInt("paymentID"));
				order.setDate(set.getDate("orderDate"));
				order.setPrice(set.getFloat("price"));
				
				result.add(order);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return result;
		
	}

	
	/**
	 * get an "id" order.
	 * 
	 * @param connection connection to the database
	 * @param id order id
	 * @return order
	 */
	public Order findById(Connection connection, int id){
		
		Order order = null;
		try {
			// write the select sql
			String sql = "SELECT * FROM orders "
							+ "WHERE orderID = ?;";
			
			// get the prepare statement
			PreparedStatement statement = connection.prepareStatement(sql);
			
			// set query parameters
			statement.setInt(1, id);
			
			// execute the sql request
			ResultSet set = statement.executeQuery();
			while(set.next()) {
				order = new Order();
				order.setId(set.getInt("orderID"));
				order.setAddressId(set.getInt("addressID"));
				order.setPaymentId(set.getInt("paymentID"));
				order.setDate(set.getDate("orderDate"));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return order;
		
	}

	
}

