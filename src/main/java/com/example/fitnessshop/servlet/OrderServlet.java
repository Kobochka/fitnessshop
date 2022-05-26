package com.example.fitnessshop.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.example.fitnessshop.bean.Product;
import com.example.fitnessshop.bean.dto.ShoppingCart;

public class OrderServlet extends HttpServlet{
	
	private final Logger LOG = Logger.getLogger(OrderServlet.class.getName());
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// get session request
		HttpSession session = req.getSession();
		
		// check if user has signed in
		if(session.getAttribute("username")!=null) {
			// get the user cart
			ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
			
			if(cart == null) { // create the cart for the first time
				cart = new ShoppingCart();
			}
			// create the new product
			Product product = new Product();
			// set the new product with the selected product inside request
			product.setId(Integer.parseInt(req.getParameter("id")));
			product.setName(req.getParameter("name"));
			product.setDescription(req.getParameter("description"));
			product.setPrice(Float.parseFloat(req.getParameter("price")));
			product.setImgPath(req.getParameter("imgPath"));
			
			
		}
		
	}
}
