package com.example.fitnessshop.filter;


import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.fitnessshop.bean.dto.ShoppingCart;

public class URLFilter implements Filter{


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		// pre-possessing 
		HttpServletRequest req = (HttpServletRequest)request;
		
		// verify that the given id parameter exists and is an number
		if(req.getServletPath().startsWith("/product")) {
			
			// check if the id parameter exist
			if(req.getParameterMap().containsKey("id")) {
				String id = req.getParameter("id");
				try {
					// check weather the given id parameter can be convert to int
					Integer.parseInt(id);
				}catch(NumberFormatException  e) {
					
					// id parameter is not an number then send the notFound page to user
					req.getRequestDispatcher("jsp/notFound.jsp").forward(request, response);
					return;
				}
					
			}else {
				
				// id parameter does not exist then send the notFound page to the user
				req.getRequestDispatcher("jsp/notFound.jsp").forward(request, response);
				return;
			}
		}
		
		// verify that checkout is accessible only when customer cart is not empty 
		if(req.getServletPath().startsWith("/checkout")) {
			
			// get user shopping cart and username attributes
			ShoppingCart cart = (ShoppingCart)req.getSession().getAttribute("cart");
			String username = (String)req.getSession().getAttribute("username");
			// check if cart is empty
			if(cart == null || cart.getVectors().size() == 0) {
				
				// the cart is empty then send user to empty cart
				req.getRequestDispatcher("jsp/shoppingCart.jsp").forward(request, response);
				return;
			}else if(username == null) {
				// get current session
				HttpSession session = req.getSession();
				// set checkout attribute in order to redirect user to checkout his cart every time he sign-in or sign.up
				session.setAttribute("checkout", "checkout");
				
				// the user has no sign-in then send him login page
				req.getRequestDispatcher("jsp/login.jsp").forward(request, response);
				return;
			}
		}
		
		// verify home and view order are accessible only when user has sign-in
		if(req.getServletPath().startsWith("/home") || req.getServletPath().startsWith("/order-history")) {
			
			// get username
			String username = (String)req.getSession().getAttribute("username");
			// check if cart is empty
			if(username == null) {
				// user has not sign-in yet then send him to login page
				req.getRequestDispatcher("jsp/login.jsp").forward(request, response);
				return;
			}
		}
		
		// take the control the next servlet or the next filter
		chain.doFilter(request, response);
		
		// post-possessing
		
	}

	@Override
	public void destroy() {

	}


}
