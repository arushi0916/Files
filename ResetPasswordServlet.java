package com.cg.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import org.apache.coyote.http11.Http11AprProcessor;
import org.apache.log4j.Logger;

import com.cg.DAO.ILoginDAO;
import com.cg.DAO.LoginDAOImpl;

import oracle.net.aso.l;

@WebServlet("/resetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet{
	
	static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ILoginDAO loginDAO = new LoginDAOImpl();
		RequestDispatcher dispatcher = null;
		
		try {
			LOGGER.info("Inside Reset Password Servlet");
			
			String newPassword = request.getParameter("password");
			String username = (String) request.getSession().getAttribute("username");
			
			if(loginDAO.resetPassword(username, newPassword) == 1) {
				request.setAttribute("successMessage", "Password updated successfully");
				String roleCode = (String) request.getSession().getAttribute("role");
				if(roleCode.equals("usr")) {
					
					LOGGER.info("Inside the login servlet method : Insured");
					dispatcher = request.getRequestDispatcher("userPage.jsp");
					dispatcher.forward(request, response);
					
				} else if(roleCode.equals("agnt")) {
					LOGGER.info("Inside the login servlet method : Handler");
					dispatcher = request.getRequestDispatcher("agent.jsp");
					dispatcher.forward(request, response);
					
				}else if(roleCode.equals("adm")) {
					LOGGER.info("Inside the login servlet method : Adjuster");
					dispatcher = request.getRequestDispatcher("admin.jsp");
					dispatcher.forward(request, response);
					
				}
			}
		} catch (Exception e) {
			
			LOGGER.error("Error while updating the password");
			
		}
	}

}