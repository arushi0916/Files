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


@WebServlet("/agentSearchBasedOnFilter")
public class AgentSearchBasedOnFilterServlet extends HttpServlet{
	
	static final Logger LOGGER = Logger.getLogger(AgentSearchBasedOnFilterServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		IAgentInterfaceDAO AgentInterfaceDAO = new AgentInterfaceDAOImpl();
		
		
		try {
			
			LOGGER.info("Inside Agent Search Based On Filter Servlet");
			
			
			String username = (String) request.getSession().getAttribute("username");
			String filter = (String) request.getParameter("filter");
			int accNum = Integer.parseInt(request.getParameter("accountNumber"));
			List<ShowClaimDetails> claimList = AgentInterfaceDAO.getClaimDetails(username,filter,accNum);
			
			
			request.setAttribute("ClaimList", claimList);
			request.getRequestDispatcher("agentShowClaimList.jsp").forward(request, response);
		} catch (Exception e) {
			LOGGER.error("Error while retriving the agent data based on filter");
		}
	}

}
