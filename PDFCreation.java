package com.cg.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.cg.DAO.GetClaimDetailsDAO;
import com.cg.DAO.IGetClaimDetails;
import com.cg.dto.Claim;
import com.cg.dto.ShowClaimDetails;
import com.cg.dto.UserResponses;

@WebServlet("/pdfCreation")
public class PDFCreation extends HttpServlet{
	
	static final Logger LOGGER = Logger.getLogger(PDFCreation.class);
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String claimNumber = (String)request.getParameter("claimNum");
		int claimN = Integer.parseInt(claimNumber);
		
		IGetClaimDetails claimDetails = new GetClaimDetailsDAO();
		Claim claim = new Claim();
		
		List<UserResponses> userList = new ArrayList<>();
		
		try {
			LOGGER.info("Inside PDFCreation servlet");
			
			claim = claimDetails.getClaim(claimN);
			userList = claimDetails.getResponses(claim.getClaimNumber());
			
		} catch (Exception e) {
			
			LOGGER.error("Error while retriving the details for PDF generartion");
			
		}
		
		
		ArrayList<ShowClaimDetails> array = (ArrayList<ShowClaimDetails>)request.getSession().getAttribute("pdfList");
		ReportPage rpage = new ReportPage(); 


		for(ShowClaimDetails details: array) {
			if(details.getClaimNumber()==Integer.parseInt(claimNumber)) {
				
				request.getSession().setAttribute("pdfPath", rpage.createPage(details, claim, userList));
				 request.getRequestDispatcher("reportGenerationDetails").forward(request, response);
			}
		}
	}
	
}
