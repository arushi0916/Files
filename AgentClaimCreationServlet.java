package com.cg.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.cg.DAO.AgentInterfaceDAOImpl;
import com.cg.DAO.IAgentInterfaceDAO;
import com.cg.dto.Claim;
import com.cg.dto.Policy;
import com.cg.utility.JDBCUtility;


@WebServlet("/agentClaimCreation")
public class AgentClaimCreationServlet extends HttpServlet{
	
	
	static final Logger LOGGER = Logger.getLogger(AgentClaimCreationServlet.class);
	
	Connection connection = null;
	PreparedStatement statement = null;
	
	IAgentInterfaceDAO agentInterfaceDAO = new AgentInterfaceDAOImpl();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			
			LOGGER.info("Inside Agent Claim Creation Servlet doGet method");
			
			String username = (String) request.getSession().getAttribute("username");
			List<Policy> list = agentInterfaceDAO.getclaimDetails(username);
			
			
			request.setAttribute("policy", list);
			
			
			request.getRequestDispatcher("agentClaimList.jsp").forward(request, response);
		} catch (Exception e) {
			LOGGER.error("Exception while displaying the claim details of the users under agent");
			
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = null ;
		
		int claimNumber = 0;
		RequestDispatcher dispatcher = null;
		try {
			
			LOGGER.info("Inside Agent Claim Creation Servlet doPost method");
			
			
			connection = JDBCUtility.getConnection();
			
			String query = "select max(claimNumber) from claim";
			statement=connection.prepareStatement(query);
			
			ResultSet res = statement.executeQuery();
			
			while(res.next()) {
				claimNumber = res.getInt(1);
			}

			
			
			String queryForUserName = "select username from userrole where accountnumber=(select accountnumber from policy where policynumber=?)"; 
			
			statement=connection.prepareStatement(queryForUserName);
			statement.setString(1, request.getParameter("policyNumber"));
			res = statement.executeQuery();
			
			
			String username="";
			
			while(res.next()) {
				username = res.getString(1);
			}
			
			
			
			Claim claim = new Claim();
			claim.setClaimReason(request.getParameter("claimReason"));
			claim.setAccidentLocationStreet(request.getParameter("location"));
			claim.setAccidentState(request.getParameter("state"));
			claim.setAccidentCity(request.getParameter("city"));
			claim.setAccidentZip(Integer.parseInt(request.getParameter("zipCode")));
			claim.setClaimType(request.getParameter("policyType"));
			claim.setPolicyNumber(Integer.parseInt(request.getParameter("policyNumber")));
			claim.setClaimNumber(++claimNumber);			
			claim.setUserName(username);
			claim.setStatus();
			
			
			int id = agentInterfaceDAO.registerClaim(claim);
			
			HttpSession session = request.getSession();
			session.setAttribute("policyNumber", Integer.parseInt(request.getParameter("policyNumber")));
			session.setAttribute("claimNumber", claimNumber);
			
			if(id==1) {
				LOGGER.info("Successfully created the claim for the user : "+username);
				request.setAttribute("policyType", request.getParameter("policyType"));
				dispatcher = request.getRequestDispatcher("questionsServlet");
				dispatcher.forward(request, response);
			}else {
				
				LOGGER.error("Error occured while creating the claim for user in the Agent servlet");
				
			}
			
		}catch (Exception e) {
			LOGGER.error("Error while creating the claim creation for user by agent");
		}
		
	}
	
}
