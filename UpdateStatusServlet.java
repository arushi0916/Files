package com.cg.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.cg.DAO.AdminInterfaceDAOImpl;
import com.cg.DAO.IAdminInterfaceDAO;


@WebServlet("/updateStatus")
public class UpdateStatusServlet extends HttpServlet{
	
	static final Logger LOGGER = Logger.getLogger(UpdateStatusServlet.class);
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		IAdminInterfaceDAO adminInterfaceDAO = new AdminInterfaceDAOImpl();
		RequestDispatcher dispatcher = null;
		try {
			LOGGER.info("Inside update status servlet");
			
			int claimNumber =Integer.parseInt(request.getParameter("claimnumber"));
			String status = request.getParameter("status");
			
			int id = adminInterfaceDAO.setStatusofClaim(claimNumber, status);
			
			if(id==1) {
				
				dispatcher = request.getRequestDispatcher("reportGenerationDetails");
				dispatcher.forward(request, response);
				
			}
		} catch (Exception e) {
			
			LOGGER.error("Error while updating the claim status");
			
		}
	}
	


}
