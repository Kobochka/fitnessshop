package com.example.fitnessshop.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.fitnessshop.dao.OrderDao;
import com.example.fitnessshop.dao.OrderItemDao;
import com.example.fitnessshop.bean.Address;
import com.example.fitnessshop.bean.Customer;
import com.example.fitnessshop.bean.Order;
import com.example.fitnessshop.bean.dto.ItemCart;
import com.example.fitnessshop.dao.AddressDao;
import com.example.fitnessshop.dao.CustomerDao;
import com.example.fitnessshop.util.Fields;

public class OrderItemServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// get order id from request
		String orderId = req.getParameter("id");
		
		// get username
		HttpSession session = req.getSession();
		String username = (String)session.getAttribute(Fields.USERNAME.toString());
 		
		// get connection to database
		Connection connection = (Connection)getServletContext().getAttribute("connection");
		
		// get items from orderId 
		OrderItemDao orderItemDao = new OrderItemDao();
		try {
			// check orderId validity
			List<ItemCart> items = orderItemDao.getItemsFromOrderId(connection, Integer.parseInt(orderId));
			
			// get current user informations
			CustomerDao customerDao = new CustomerDao();
			AddressDao addressDao = new AddressDao();
			OrderDao orderDao = new OrderDao();
			Customer customer = customerDao.getCustomerByUsername(connection, username);
			Address address = addressDao.findById(connection, customer.getId());
			Order order = orderDao.findById(connection, Integer.parseInt(orderId));
			
			if(items == null) { // the orderId does not exist
				req.getRequestDispatcher("").forward(req, resp);
				return;
			}
			// pass the control to orderItems.jsp
			req.setAttribute("items", items);
			req.setAttribute("customer", customer);
			req.setAttribute("address", address);
			req.setAttribute("order", order);
			req.getRequestDispatcher("jsp/orderItems.jsp").forward(req, resp);
			
		}catch (NumberFormatException e) { // the orderId is not valid
			req.getRequestDispatcher("").forward(req, resp);
			return;
		}
	}
	
	
}
