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
import com.cg.dto.Policy;


@WebServlet("/searchUserName")
public class AgentSearchBasedOnUserNameServlet extends HttpServlet{
	
	static final Logger LOGGER = Logger.getLogger(AgentSearchBasedOnUserNameServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IAgentInterfaceDAO agentInterfaceDAO = new AgentInterfaceDAOImpl();
		
		try {
			
			LOGGER.info("Inside Agent Search Based On UserName Servlet");
			
			String username = (String) request.getSession().getAttribute("username");
			String clientUsername = request.getParameter("clientUsername");
			List<Policy> list = agentInterfaceDAO.getclaimDetails(username,clientUsername);
			
			request.setAttribute("policy", list);
			
			
			request.getRequestDispatcher("agentClaimList.jsp").forward(request, response);
		} catch (Exception e) {
			
			LOGGER.error("Exception while displaying the claim data for agent based on UserName");
			
		}
	}
}
