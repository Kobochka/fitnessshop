package com.example.fitnessshop.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.fitnessshop.dao.OrderDao;
import com.example.fitnessshop.bean.Order;
import com.example.fitnessshop.util.Fields;

public class OrderHistoryServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get session
		HttpSession session = req.getSession();
		
		// get username
		String username = (String)session.getAttribute(Fields.USERNAME.toString());
		
		// get db connection
		Connection connection = (Connection)getServletContext().getAttribute("connection");
		
		// get orders form db
		OrderDao orderDao = new OrderDao();
		List<Order> orders = new ArrayList<>();
		orders = orderDao.findOrderByUsername(connection, username);
		
		// sent user orderHistory.jsp
		req.setAttribute("orders", orders);
		req.getRequestDispatcher("jsp/orderHistory.jsp").forward(req, resp);;
	}
}
