package com.example.fitnessshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.fitnessshop.bean.dto.ItemCart;

public class OrderItemDao {

private final Logger LOG = Logger.getLogger(OrderItemDao.class.getName());
	
	/**
	 * Create a new order item.
	 * 
	 * @param connection connection to the database
	 * @param itemCart itemCart to save
	 * @param orderId id of order
	 * @return persisted orderItem
	 */
	public ItemCart createOrderItem(Connection connection, ItemCart itemCart, int orderId) {
		try {
			
			// write the SQL insert
			String sql = "INSERT INTO orderItems(orderID, productID,"
								+ " orderItemQuantity) "
								+ "	VALUES(?,?,?);";
			// get preparedStatement
			PreparedStatement statement = connection.prepareStatement(sql);
		    // set SQL parameters
			statement.setInt(1, orderId);
			statement.setInt(2, itemCart.getId());
			statement.setInt(3, itemCart.getQuantity());
			
			// execute the query
			int rows = statement.executeUpdate();
			
			// check if orderItem entity is created
			if (rows == 0) {
	            throw new SQLException("Failled to create orderItem, no rows affected.");
	        }
			
			
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, e, null);
		}
		
		return itemCart;
	}
	
	/**
	 * get items of an order.
	 * 
	 * @param connection connection to the database
	 * @param orderId order id
	 * @return list of ItemCart
	 */
	public List<ItemCart> getItemsFromOrderId(Connection connection, int orderId) {
		
		List<ItemCart> result = new ArrayList<ItemCart>();
		try {
			// write the SQL insert
			String sql = "SELECT * "
							+ "FROM orderItems "
							+ "INNER JOIN products "
								+ "ON products.productID = orderItems.productID "
							+ "WHERE orderID = ?;";
			// get preparedStatement
			PreparedStatement statement = connection.prepareStatement(sql);
		    // set SQL parameters
			statement.setInt(1, orderId);
			
			// execute the query
			ResultSet set = statement.executeQuery();
			while(set.next()) {
				ItemCart itemCart = new ItemCart();
				itemCart.setId(set.getInt("productID"));
				itemCart.setName(set.getString("productName"));
				itemCart.setPrice(set.getFloat("productPrice"));
				itemCart.setQuantity(set.getInt("orderItemQuantity"));
				itemCart.setImgPath(set.getString("productImgPath"));
				result.add(itemCart);
			}
			
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, e, null);
		}
		
		return result;
	}

}
