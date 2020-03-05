package com.cg.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.cg.DAO.AdminInterfaceDAOImpl;
import com.cg.DAO.IAdminInterfaceDAO;
import com.cg.dto.Policy;
import com.cg.exceptions.InsuranceException;
import com.cg.utility.JDBCUtility;


@WebServlet("/addPolicyServlet")
public class AddPolicyServlet extends HttpServlet{
	
	Connection connection = null;
	PreparedStatement statement = null;
	
	static final Logger LOGGER = Logger.getLogger(AddPolicyServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IAdminInterfaceDAO adminInterfaceDAO = new AdminInterfaceDAOImpl();
		RequestDispatcher dispatcher=null;
		PrintWriter out = null;
		
		try {
			
			LOGGER.info("Inside the Add Policy servlet.");
			
			connection = JDBCUtility.getConnection();
			int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
			String policyType = request.getParameter("policyType");
			
			if (!adminInterfaceDAO.checkPolicy(policyType, accountNumber)) {
				
				String query = "select max(policyNumber) from policy";
				statement = connection.prepareStatement(query);
				ResultSet res = statement.executeQuery();
				int policyNumber = 0;
				while (res.next()) {
					policyNumber = res.getInt(1);
				}
				Policy policy = new Policy();
				policy.setPolicyNumber(++policyNumber);
				policy.setAccountNumber(accountNumber);
				policy.setPolicyPremium(Integer.parseInt(request.getParameter("policyPremium")));
				policy.setPolicyType(policyType);
				int result = adminInterfaceDAO.addPolicy(policy);
				if (result == 1) {
					request.setAttribute("successMessage", "Policy Added Successfully");
					dispatcher = request.getRequestDispatcher("admin.jsp");
					dispatcher.forward(request, response);
				} else {
					LOGGER.info("Could not add the policy details.");
					out.println(
							"<h1><font color = 'red'> Encountered one problem please try again after some time. </font></h1>");
					dispatcher = request.getRequestDispatcher("addPolicy.jsp");
					dispatcher.forward(request, response);
				} 
			}else {
				request.setAttribute("errormessage", "Policy already exist");
				dispatcher = request.getRequestDispatcher("addPolicy.jsp");
				dispatcher.forward(request, response);
			}
			
		} catch (Exception e) {
			LOGGER.error("Error while adding the policy details data ");

		} 
		
	}
	
}
