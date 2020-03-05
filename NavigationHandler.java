package com.cg.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebServlet("/navHandler")
public class NavigationHandler extends HttpServlet{

	static final Logger LOGGER = Logger.getLogger(NavigationHandler.class);
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LOGGER.info("Inside Navigation Handler Servlet");
		
		// handling flow of navigation bar
		String operation = request.getParameter("operation").toString();
		LOGGER.info(operation);
		
		//accessing the operation from url of navigation bar
		RequestDispatcher dispatcher = null;
		if(request.getSession(false).getAttribute("username")!=null) { // first validation of session object of username
			if(operation.equals("home")) {
				String role = request.getSession(false).getAttribute("role").toString();
				if(role.equals("usr")) {
					dispatcher = request.getRequestDispatcher("userPage.jsp");
					dispatcher.forward(request, response);
				} else if(role.equals("agnt")) {
					dispatcher = request.getRequestDispatcher("agent.jsp");
					dispatcher.forward(request, response);
				}else if(role.equals("adm")) {
					dispatcher = request.getRequestDispatcher("admin.jsp");
					dispatcher.forward(request, response);
				}
			}
					
					// all other navigation functionality will be added here
					
		}
		dispatcher = request.getRequestDispatcher("login.html");
		dispatcher.forward(request, response);
		
	}
}
