package com.cg.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.cg.DAO.AdminInterfaceDAOImpl;
import com.cg.DAO.IAdminInterfaceDAO;
import com.cg.DAO.IUserInterfaceDAO;
import com.cg.DAO.UserInterfaceDAOImpl;
import com.cg.dto.Policy;
import com.cg.dto.Questions;

@WebServlet("/questionsServlet")
public class QuestionsServlet extends HttpServlet{

	static final Logger LOGGER = Logger.getLogger(QuestionsServlet.class);
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		IUserInterfaceDAO userInterfaceDAO = new UserInterfaceDAOImpl();
		RequestDispatcher dispatcher = null;
		
		
		try {
			
			LOGGER.info("Inside Questions Servlet");
			
			String policyType= (String) request.getAttribute("policyType");
			List<Questions> list = userInterfaceDAO.getQuestions(policyType);
			
			request.setAttribute("questionsList", list);
			
			request.getRequestDispatcher("displayQuestion.jsp").forward(request, response);
			
		} catch (Exception e) {
			
			LOGGER.error("Error while retriving the question for the policy");
		}
	}

}
