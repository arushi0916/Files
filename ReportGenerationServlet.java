package com.cg.controller;

import java.io.IOException;
import java.util.List;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.cg.DAO.AdminInterfaceDAOImpl;
import com.cg.DAO.IAdminInterfaceDAO;
import com.cg.dto.ShowClaimDetails;


@WebServlet("/reportGenerationDetails")
public class ReportGenerationServlet extends HttpServlet{
	
	static final Logger LOGGER = Logger.getLogger(ReportGenerationServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IAdminInterfaceDAO adminInterfaceDAO = new AdminInterfaceDAOImpl();
		
		try {
			
			LOGGER.info("Inside Report Generation Servlet doGet Method");
			
			String username = (String) request.getSession().getAttribute("username");
			List<ShowClaimDetails> claimList = adminInterfaceDAO.getClaimDetailsForReportGeneration();
			
			
			request.setAttribute("ClaimList", claimList);
			request.getRequestDispatcher("ReportGenerationScreen.jsp").forward(request, response);
		} catch (Exception e) {
			
			LOGGER.error("Error in while retriving the data for report generation screen");
			
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IAdminInterfaceDAO adminInterfaceDAO = new AdminInterfaceDAOImpl();
		
		try {
			LOGGER.info("Inside Report Generation Servlet doPost Method");
			
			String username = (String) request.getSession().getAttribute("username");
			String filter = (String) request.getParameter("filter");
			int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
			
			
			
			List<ShowClaimDetails> claimList = adminInterfaceDAO.getSearchedClaimDetails(accountNumber, filter);
			
			
			request.setAttribute("ClaimList", claimList);
			request.getRequestDispatcher("ReportGenerationScreen.jsp").forward(request, response);
		} catch (Exception e) {
			
			LOGGER.error("Error while retriving the data for report generation screen based on Filter");
			
		}
	}
	
	

}
