package com.example.fitnessshop.listener;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.example.fitnessshop.dao.DBConnection;

@WebListener()
public class ApplicationListener implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		// get connection to the database
		
		System.out.println("I m in context Initialization method");
		
		Connection connection = DBConnection.getConncetionToDatabase();
		
		// verify the successfull connection to the database
//		if(connection != null) {
//			
//		}
		
		// add connection to the contextServlet
		 sce.getServletContext().setAttribute("connection", connection);
		 
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
		
		// get connection to the database
		Connection connection = (Connection)sce.getServletContext().getAttribute("connection");
		System.out.println("I am in the contextdestroyed method");
		try {
			
			// close connection on undeployment
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
