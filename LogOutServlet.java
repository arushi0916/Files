package com.cg.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.log4j.Logger;

import com.cg.DAO.ILoginDAO;
import com.cg.DAO.LoginDAOImpl;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet{

	static final Logger LOGGER = Logger.getLogger(LogOutServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LOGGER.info("Inside LogOut Servlet");
		
		if(request.getSession().getAttribute("username")==null)response.sendRedirect("login.html");
		request.getSession(false).invalidate();
		response.sendRedirect("login.jsp");
	}
}
