package com.example.fitnessshop.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.fitnessshop.dao.ProductDao;
import com.example.fitnessshop.bean.Product;


//@WebServlet("/product")
public class ProductServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// get product id from request
		String id = req.getParameter("id");
		System.out.println("Id to fetch: " + id);
		
		
		// get connection to database
		Connection connection = (Connection)getServletContext().getAttribute("connection");
		
		// get the "id" product
		ProductDao productDao = new ProductDao();
		try {
			// check id validity
			Product product = productDao.findById(connection, Integer.parseInt(id));
			
			if(product == null) { // the id does not exist
				req.getRequestDispatcher("").forward(req, resp);
				return;
			}
			// pass the control to product.jsp
			req.setAttribute("product", product);
			req.getRequestDispatcher("jsp/product.jsp").forward(req, resp);
			
		}catch (NumberFormatException e) { // the id is not valid
			req.getRequestDispatcher("").forward(req, resp);
			return;
		}
	}
	
	
}
