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

import org.apache.log4j.Logger;

import com.cg.DAO.AdminInterfaceDAOImpl;
import com.cg.DAO.IAdminInterfaceDAO;
import com.cg.DAO.ILoginDAO;
import com.cg.DAO.IUserInterfaceDAO;
import com.cg.DAO.LoginDAOImpl;
import com.cg.DAO.UserInterfaceDAOImpl;
import com.cg.dto.PolicyDetails;


@WebServlet("/policyDetailsServlet")
public class PolicyDetailsServlet extends HttpServlet{
	
	static final Logger LOGGER = Logger.getLogger(PolicyDetailsServlet.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LOGGER.info("Inside Policy Details Servlet");
		
		IUserInterfaceDAO userInterfaceDAO = new UserInterfaceDAOImpl();
		ILoginDAO loginDAO = new LoginDAOImpl();
		
		
		PrintWriter out = null;
		RequestDispatcher dispatcher = null;
		String roleCode="";
		try {
			
			int noOfQuestions = (int) request.getSession().getAttribute(("noOfQuestions"));
			
			int res = 0;
			for(int i=1;i<=noOfQuestions;i++) {
				String id = "q"+i;
				String ans = ""+i;
			
				PolicyDetails policyDetails = new PolicyDetails();
				policyDetails.setQuestionId((String)request.getSession().getAttribute((id)));
				policyDetails.setAnswer(request.getParameter(ans));
				policyDetails.setClaimNumber((int)request.getSession().getAttribute("claimNumber"));
				policyDetails.setPolicyNumber((int)request.getSession().getAttribute("policyNumber"));
				res += userInterfaceDAO.addPolicyDetails(policyDetails);
				
			}
			if(res==noOfQuestions) {
				
				LOGGER.info("Added all questions into the database");
				HttpSession session = request.getSession();
				roleCode = loginDAO.getRoleCode((String)session.getAttribute("username"));
				
				if(roleCode.equals("usr")) {
					request.setAttribute("successMessage", "Claim Created Successfully");
					dispatcher = request.getRequestDispatcher("userPage.jsp");
					dispatcher.forward(request, response);
				}else if(roleCode.equals("agnt")){
					request.setAttribute("successMessage", "Claim Created Successfully");
					dispatcher = request.getRequestDispatcher("agent.jsp");
					dispatcher.forward(request, response);
				}

				
			}else {
				
				LOGGER.error("Encountered some problem please try again after some time");				
				dispatcher = request.getRequestDispatcher("displayQuestions.jsp");
				dispatcher.forward(request, response);
			}
			
			
			
		}catch(Exception e) {
			LOGGER.error("Error while entering the Policy details");
			request.setAttribute("errormessage", "Claim creation not successful!");
			if(roleCode.equals("usr")) {
				dispatcher = request.getRequestDispatcher("userPage.jsp");
				dispatcher.forward(request, response);
			}else if(roleCode.equals("agnt")){
				dispatcher = request.getRequestDispatcher("agent.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
}
