package com.example.fitnessshop.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.fitnessshop.dao.UserDao;
import com.example.fitnessshop.validation.Validation;
import com.example.fitnessshop.bean.Address;
import com.example.fitnessshop.bean.Customer;
import com.example.fitnessshop.bean.User;
import com.example.fitnessshop.dao.AddressDao;
import com.example.fitnessshop.dao.CustomerDao;
import com.example.fitnessshop.util.Fields;
import com.example.fitnessshop.util.Utils;

public class RegisterServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get dispatcher and send control to register.jsp
		req.getRequestDispatcher("jsp/register.jsp").forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");

		// validate request parameters and get errors messages
		Map<String, String> errors = Validation.validateSignUpForm(req);
		
		
		if(errors.size() == 0) { // all parameters are valid
			
			// get DB connection from servlet context
			Connection connection = (Connection)getServletContext().getAttribute("connection");
			
			// check weather the username is still available
			String username = req.getParameter(Fields.USERNAME.toString());
			UserDao userDao = new UserDao();
			
			if(userDao.isUsernameAvailable(connection, username)) {
					
				// get parameters request
				String password = req.getParameter(Fields.PASSWORD.toString());
				String firstName = req.getParameter(Fields.FIRSTNAME.toString());
				String lastName = req.getParameter(Fields.LASTNAME.toString());
				String email = req.getParameter(Fields.EMAIL.toString());
				String phone = req.getParameter(Fields.PHONE.toString());
				String birthdate = req.getParameter(Fields.BIRTHDATE.toString());
				String country = req.getParameter(Fields.COUNTRY.toString());
				String street = req.getParameter(Fields.STREET.toString());
				String zipCode = req.getParameter(Fields.ZIPCODE.toString());
				String city = req.getParameter(Fields.CITY.toString());
				
				// create beans
				User user = new User(username, password);
				Customer customer = new Customer(firstName, lastName, email, phone, Utils.formatDate(birthdate));
				Address address = new Address(street, zipCode, city, country, Address.Status.PRINCIPALE.toString());
				
				// register new customer credentials
				user = userDao.registerUser(connection, user);
				System.out.println("Register username successfully!");
				
				// register new customer billing informations
				CustomerDao customerDao = new CustomerDao();
				customer = customerDao.registerCustomer(connection, customer, user.getId());
				
				// register new customer address
				AddressDao addressDao = new AddressDao();
				address = addressDao.registerAddress(connection, address, customer.getId());
				
				// verify that user has checkout
				HttpSession session = req.getSession();
				String checkout = (String)session.getAttribute("checkout");
				if(checkout != null) {
					System.out.println("Checkout has been visted in register");
					// then send user to checkout his cart
					session.setAttribute("username", user.getUsername());
					req.getRequestDispatcher("checkout").forward(req, resp);
					return;
				}
				
				// redirect the user to the login.jsp to sign-in with success message
				req.setAttribute("isRegistered", true);
				req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
				return;
				
			}else {
				errors.put(Fields.USERNAME.toString(), "Username: " + username + " is not available!");
			}
			
		}
		req.setAttribute("errors", errors);
		req.getRequestDispatcher("jsp/register.jsp").forward(req, resp);
		
	} 
	
}
