package com.cg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.controller.LoginServlet;
import com.cg.dto.Claim;
import com.cg.dto.Questions;
import com.cg.dto.UserResponses;
import com.cg.exceptions.InsuranceException;
import com.cg.queries.QueryConstants;
import com.cg.utility.JDBCUtility;

public class GetClaimDetailsDAO implements IGetClaimDetails {
	
	Connection connection = null;
	PreparedStatement statement = null;

	static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
	
	@Override
	public Claim getClaim(int claimNumber) throws Exception {

		connection = JDBCUtility.getConnection();
		Claim claim = new Claim();
		
		try {
			
			LOGGER.info("Inside get claim method in get claim details DAO Implementation");
			
			String query = QueryConstants.GET_CLAIM_DETAILS_USING_CLAIMNUMBER;
			statement = connection.prepareStatement(query);
			statement.setInt(1, claimNumber);
			ResultSet res = statement.executeQuery();
			
			while(res.next()) {
				
				claim.setClaimNumber(res.getInt(1));
				claim.setClaimReason(res.getString(2));
				claim.setAccidentLocationStreet(res.getString(3));
				claim.setAccidentCity(res.getString(4));
				claim.setAccidentState(res.getString(5));
				claim.setAccidentZip(res.getInt(6));
				claim.setClaimType(res.getString(7));
				claim.setPolicyNumber(res.getInt(8));
				claim.setUserName(res.getString(9));
	
			}
		} catch (Exception e) {

			LOGGER.error("Error in questions creation DAO ");
		}
		return claim;
	}
	
	public List<UserResponses> getResponses(int claimNumber) throws InsuranceException {
		connection = JDBCUtility.getConnection();
		List<UserResponses> responseList = new ArrayList<>();
		
		try {
			LOGGER.info("Inside get response method in get claim details DAO Implementation");
			
			
			String query = "select q.question, p.answer from questions q, policydetails p where claimNumber=? and q.questionid=p.questionid";
			statement = connection.prepareStatement(query);
			statement.setInt(1, claimNumber);
			ResultSet res = statement.executeQuery();
			
			UserResponses userResponses = null;
			
			while(res.next()) {
				
				userResponses = new UserResponses();
				userResponses.setQuestion(res.getString("question"));
				userResponses.setAnswer(res.getString("answer"));
				responseList.add(userResponses);
				
			}
		} catch (Exception e) {

			LOGGER.error("Error while retriving the responses in get claim details DAO ");
			
		}
		return responseList;
		
	}

}
