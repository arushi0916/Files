package com.cg.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.cg.DAO.IUserInterfaceDAO;
import com.cg.DAO.UserInterfaceDAOImpl;
import com.cg.dto.ShowClaimDetails;


@WebServlet("/userShowClaimDetails")
public class UserShowClaimDetailsServlet extends HttpServlet{
	
	static final Logger LOGGER = Logger.getLogger(UserShowClaimDetailsServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IUserInterfaceDAO userInterfaceDAO  = new UserInterfaceDAOImpl();
		
		try {
			LOGGER.info("Inside User Show Claim Details Servlet");
			
			String username = (String) request.getSession().getAttribute("username");
			List<ShowClaimDetails> claimList = userInterfaceDAO.getClaimDetails(username);
			
			
			request.setAttribute("ClaimList", claimList);
			request.getRequestDispatcher("userShowClaimList.jsp").forward(request, response);
			
		} catch (Exception e) {
			
			LOGGER.error("Error while displaying the claim details for the user");
			
		}
	}

}
