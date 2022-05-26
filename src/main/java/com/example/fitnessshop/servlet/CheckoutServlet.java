package com.example.fitnessshop.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.fitnessshop.bean.Address;
import com.example.fitnessshop.bean.Customer;
import com.example.fitnessshop.dao.AddressDao;
import com.example.fitnessshop.dao.CustomerDao;

public class CheckoutServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// get session
		HttpSession session = req.getSession();
		// get username
		String username = (String)session.getAttribute("username");
		
		// get current user informations
		Connection connection = (Connection)getServletContext().getAttribute("connection");
		CustomerDao customerDao = new CustomerDao();
		AddressDao addressDao = new AddressDao();
		Customer customer = customerDao.getCustomerByUsername(connection, username);
		Address address = addressDao.getAddressByCustomerId(connection, customer.getId());
		
		// get request dispatcher and send control to checkout.jsp
		req.setAttribute("customer", customer);
		req.setAttribute("address", address);
		req.getRequestDispatcher("jsp/checkout.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
