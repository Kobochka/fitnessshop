package com.example.fitnessshop.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.fitnessshop.dao.ProductDao;
import com.example.fitnessshop.bean.Product;


public class IndexServlet extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// get connection from ServletContext
		Connection connection = (Connection)getServletContext().getAttribute("connection");
		
		// create an instance of productDao
		ProductDao productDao = new ProductDao();
		
		// fetch all products
		List<Product> products = productDao.findAll(connection);
		
		// transfers products attribute to index.jsp
		req.setAttribute("products", products);
		
		// passe the control to the index.jsp
		req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
