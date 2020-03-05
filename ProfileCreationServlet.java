package com.cg.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebFault;

import org.apache.log4j.Logger;

import com.cg.DAO.AdminInterfaceDAOImpl;
import com.cg.DAO.IAdminInterfaceDAO;
import com.cg.dto.UserRole;
@WebServlet("/profileCreation")
public class ProfileCreationServlet extends HttpServlet{
	
	static final Logger LOGGER = Logger.getLogger(ProfileCreationServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IAdminInterfaceDAO adminInterfaceDAO = new AdminInterfaceDAOImpl();
		
		PrintWriter out = response.getWriter();
		RequestDispatcher dispatcher=null;
		
		try {
			
			LOGGER.info("Inside Profile Creation Servlet.");
			
			String userName = request.getParameter("userName");
			int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
			
			if (!adminInterfaceDAO.checkProfile(userName, accountNumber)) {
				String agentId = "";
				if (request.getParameter("roleCode").equals("usr")) {
					agentId = request.getParameter("agentId");
				} else
					agentId = null;
				UserRole userRole = new UserRole();
				userRole.setUserName(userName);
				userRole.setPassword(request.getParameter("password"));
				userRole.setRoleCode(request.getParameter("roleCode"));
				userRole.setAgentId(agentId);
				userRole.setAccountNumber(accountNumber);
				int id = adminInterfaceDAO.createProfile(userRole);
				if (id == 1) {
					LOGGER.info("Successfully created profile");
					request.setAttribute("successMessage", "Created Profile Successfully");
					dispatcher = request.getRequestDispatcher("admin.jsp");
					dispatcher.forward(request, response);

				} else {

					LOGGER.info("Couldn't create the profile");
					out.println(
							"<h1><font color = 'red'> Encountered one problem please try again after some time. </font></h1>");
					dispatcher = request.getRequestDispatcher("loginErrorPage.html");
					dispatcher.forward(request, response);
				} 
			}else {
				
				/*Username or account number already exists*/
				request.setAttribute("errormessage", "User name or account number already exist");
				dispatcher = request.getRequestDispatcher("profileCreation.jsp");
				dispatcher.forward(request, response);
				
			}
		} catch (Exception e) {
			
			LOGGER.error("Error While creating the profile");
		}
	}
}
