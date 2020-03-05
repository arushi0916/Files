package com.cg.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.cg.DAO.AgentInterfaceDAOImpl;
import com.cg.DAO.IAgentInterfaceDAO;
import com.cg.dto.ShowClaimDetails;


@WebServlet("/agentShowClaimDetails")
public class AgentShowClaimDetailsServlet extends HttpServlet{
	
	static final Logger LOGGER = Logger.getLogger(AgentShowClaimDetailsServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IAgentInterfaceDAO agentInterfaceDAO = new AgentInterfaceDAOImpl();
		
		try {
			
			LOGGER.info("Inside Agent Show Claim Details Servlet");
			
			String username = (String) request.getSession().getAttribute("username");
			List<ShowClaimDetails> claimList = agentInterfaceDAO.getClaimDetails(username);
			
			
			request.setAttribute("ClaimList", claimList);
			request.getRequestDispatcher("agentShowClaimList.jsp").forward(request, response);
			
		} catch (Exception e) {
			
			LOGGER.error("Error while displaying the claim details for Agent");
			
		}
	}

}
