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
import com.example.fitnessshop.bean.User;
import com.example.fitnessshop.util.Fields;
import com.example.fitnessshop.validation.Validation;

public class LoginServlet extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get request dispatcher and send control to login.jsp
		req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// validate request fields
		Map<String, String> errors = Validation.validateSignInForm(req);
		HttpSession captchaSession = req.getSession();
		String captchaText = String.valueOf(captchaSession.getAttribute("captcha_security"));

		if(!req.getParameter("captcha").equals(captchaText)){
			req.setAttribute("isInvalidCaptcha", true);
			req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
			return;
		}
		if(errors.size() == 0) {
			// get user credentials
			String username = req.getParameter(Fields.USERNAME.toString());
			String password = req.getParameter(Fields.PASSWORD.toString());
			User user = new User(username, password);
			
			// authenticate user
			UserDao userDao = new UserDao();
			Connection connection = (Connection)getServletContext().getAttribute("connection");
			boolean isAuthenticated = userDao.validateUser(connection, user);
			
			if(isAuthenticated) { // user is authenticated
				// set session with username
				HttpSession session = req.getSession();
				session.setAttribute("username", user.getUsername());
				
				// verify that user has checkout
				String checkout = (String)session.getAttribute("checkout");
				if(checkout != null) {
					
					System.out.println("Checkout has been visited login");
					// then send user to checkout his cart
					req.getRequestDispatcher("checkout").forward(req, resp);
					return;
				}
				
				// send user to home.jsp
				req.getRequestDispatcher("home").forward(req, resp);
				return;
			}else {
				req.setAttribute("isInvalidCredentials", true);
			}
		}
		req.setAttribute("errors", errors);
		req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
	}
}
