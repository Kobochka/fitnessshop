package com.example.fitnessshop.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.fitnessshop.validation.Validation;
import com.example.fitnessshop.bean.Address;
import com.example.fitnessshop.bean.Customer;
import com.example.fitnessshop.dao.AddressDao;
import com.example.fitnessshop.dao.CustomerDao;
import com.example.fitnessshop.util.Fields;

public class CheckoutAddressServlet extends HttpServlet{
	
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
		
		// get request dispatcher and send control to checkoutAddress.jsp
		req.setAttribute("customer", customer);
		req.setAttribute("address", address);
		req.getRequestDispatcher("jsp/checkoutAddress.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get session
		HttpSession session  = req.getSession();
		// get usrname
		String username = (String) session.getAttribute(Fields.USERNAME.toString());
		
		// validate request parameters and get errors messages
		Map<String, String> errors = Validation.validateAddressCheckoutForm(req);
		
		if(errors.size() == 0) { // all parameters are valid
		
			// get request parameters
			String firstName = req.getParameter(Fields.FIRSTNAME.toString());
			String lastName = req.getParameter(Fields.LASTNAME.toString());
			String country = req.getParameter(Fields.COUNTRY.toString());
			String street = req.getParameter(Fields.STREET.toString());
			String zipCode = req.getParameter(Fields.ZIPCODE.toString());
			String city = req.getParameter(Fields.CITY.toString());
			
			// get user informations
			Connection connection = (Connection)getServletContext().getAttribute("connection");
			CustomerDao customerDao = new CustomerDao();
			AddressDao addressDao = new AddressDao();
			Customer customer = customerDao.getCustomerByUsername(connection, username);
			Address address = addressDao.getAddressByCustomerId(connection, customer.getId());
			
			// set customer first and last Name
			customer.setFirstName(firstName);
			customer.setLastName(lastName);
			customerDao.updateCustomer(connection, customer);
			
			// change the customer address status to secondary
			address.setStatus(Address.Status.SECONDARY.toString());
			addressDao.updateAddress(connection, address);
			
			// register the new address as the principal address of the customer
			Address newAddress = new Address(street, zipCode, city, country, Address.Status.PRINCIPALE.toString());
			addressDao.registerAddress(connection, newAddress, customer.getId());
			
			// sent user to proceed with checkout
			req.getRequestDispatcher("checkout").forward(req, resp);
			return;
		}
		req.setAttribute("errors", errors);
		req.getRequestDispatcher("jsp/checkoutAddress.jsp").forward(req, resp);
		
		
	}
}
