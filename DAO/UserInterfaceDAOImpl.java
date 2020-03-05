package com.cg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.dto.Claim;
import com.cg.dto.Policy;
import com.cg.dto.PolicyDetails;
import com.cg.dto.Questions;
import com.cg.dto.ShowClaimDetails;
import com.cg.queries.QueryConstants;
import com.cg.utility.JDBCUtility;

public class UserInterfaceDAOImpl implements IUserInterfaceDAO{

	Connection connection = null;
	PreparedStatement statement = null;
	
	static final Logger LOGGER = Logger.getLogger(AdminInterfaceDAOImpl.class);
	
	@Override
	public List<Policy> getclaimDetails(String username) throws Exception {
		List<Policy> listOfPolicies = new ArrayList<Policy>();
		connection = JDBCUtility.getConnection();
		
		try {
			LOGGER.info("Inside the get claim details method in UserDAO Implementation");
			
			String queryAccountNum = QueryConstants.GET_ACCOUNT_NUMBER_IN_USER;
			statement=connection.prepareStatement(queryAccountNum);
			statement.setString(1, username);
			
			ResultSet res = statement.executeQuery();
			int accNum = 0;
			while(res.next()) {
				accNum=res.getInt("accountNumber");
			}
			
			
			String query = QueryConstants.GET_CLAIM_DETAILS_USING_ACCOUNTNUMBER;
			statement=connection.prepareStatement(query);
			statement.setInt(1, accNum);
			res = statement.executeQuery();
			while(res.next()) {
				
				Policy policy = new Policy();
				
				policy.setPolicyNumber(res.getInt("policyNumber"));
				policy.setPolicyPremium(res.getInt("policyPremium"));
				policy.setAccountNumber(res.getInt("accountNumber"));
				policy.setPolicyType(res.getString("policyType"));
				listOfPolicies.add(policy);
			}
	
		}catch(Exception e) {
			
			LOGGER.error("Error while retriving the claim deatls in UserDAO Implementation");
			
		}finally {
			connection.close();
		}
		return listOfPolicies;
	}
	
	
	@Override
	public int registerClaim(Claim claim) throws Exception {
		connection = JDBCUtility.getConnection();
		int res=0;
		
		try {
			LOGGER.info("Inside the register claim method in UserDAO Implementation");
			
			String query=QueryConstants.ADD_CLAIM;
			
			statement = connection.prepareStatement(query);
			statement.setInt(1, claim.getClaimNumber());
			statement.setString(2, claim.getClaimReason());
			statement.setString(3, claim.getAccidentLocationStreet());
			statement.setString(4, claim.getAccidentCity());
			statement.setString(5, claim.getAccidentState());
			statement.setInt(6, claim.getAccidentZip());
			statement.setString(7, claim.getClaimType());
			statement.setInt(8, claim.getPolicyNumber());
			statement.setString(9, claim.getUserName());
			statement.setString(10, claim.getStatus());
			
			res = statement.executeUpdate();
			
		} catch (Exception e) {
			
			LOGGER.error("Error while registering the claim for user in UserDAO Implementation");
			
		}
		
		return res;
	}
	
	
	@Override
	public List<ShowClaimDetails> getClaimDetails(String username) throws Exception {
		List<ShowClaimDetails> showClaimDetailsList = new ArrayList<ShowClaimDetails>();
		connection = JDBCUtility.getConnection();
		
		try {
			LOGGER.info("Inside the get claim details method using username in UserDAO Implementation");
			
			String queryAccountNum = QueryConstants.GET_ACCOUNT_NUMBER_IN_USER;
			statement=connection.prepareStatement(queryAccountNum);
			statement.setString(1, username);
			
			ResultSet res = statement.executeQuery();
			int accNum = 0;
			while(res.next()) {
				accNum=res.getInt("accountNumber");
			}
			
			String query = QueryConstants.DISPLAY_CLAIM_DETAILS;
			statement=connection.prepareStatement(query);
			statement.setInt(1, accNum);
			
			res = statement.executeQuery();
			
			while(res.next()) {
				
				ShowClaimDetails claimDetails = new ShowClaimDetails();
				
				claimDetails.setAccountNumber(res.getInt("accountNumber"));
				claimDetails.setPolicyNumber(res.getInt("policyNumber"));
				claimDetails.setPolicyPremium(res.getInt("policyPremium"));
				claimDetails.setPolicyType(res.getString("policyType"));
				claimDetails.setClaimNumber(res.getInt("claimNumber"));
				claimDetails.setStatus(res.getString("status"));
				showClaimDetailsList.add(claimDetails);
			}
	
		}catch(Exception e) {
			
			LOGGER.error("Error while retriving the claim details using username in UserDAO Implementation");
			
		}finally {
			connection.close();
		}
		return showClaimDetailsList;
	}
	
	
	@Override
	public List<ShowClaimDetails> getClaimDetails(String username, String filter) throws Exception {
		
		List<ShowClaimDetails> showClaimDetailsList = new ArrayList<ShowClaimDetails>();
		connection = JDBCUtility.getConnection();
		
		try {
			LOGGER.info("Inside the get claim details method using filter in UserDAO Implementation");
			
			String queryAccountNum = QueryConstants.GET_ACCOUNT_NUMBER_IN_USER;
			statement=connection.prepareStatement(queryAccountNum);
			statement.setString(1, username);
			
			ResultSet res = statement.executeQuery();
			int accNum = 0;
			while(res.next()) {
				accNum=res.getInt("accountNumber");
			}
			
			String query="";
			
			if(filter.equals("all")) {
				
				query = QueryConstants.DISPLAY_CLAIM_DETAILS;;
				statement=connection.prepareStatement(query);
				statement.setInt(1, accNum);
				
			}else {
				
				query = QueryConstants.DISPLAY_CLAIM_DETAILS_USING_STATUS;
				statement=connection.prepareStatement(query);
				statement.setString(2, filter);
				statement.setInt(1, accNum);
				
			}
			
			res = statement.executeQuery();
			
			while(res.next()) {
				
				ShowClaimDetails claimDetails = new ShowClaimDetails();
				
				claimDetails.setAccountNumber(res.getInt("accountNumber"));
				claimDetails.setPolicyNumber(res.getInt("policyNumber"));
				claimDetails.setPolicyPremium(res.getInt("policyPremium"));
				claimDetails.setPolicyType(res.getString("policyType"));
				claimDetails.setClaimNumber(res.getInt("claimNumber"));
				claimDetails.setStatus(res.getString("status"));
				showClaimDetailsList.add(claimDetails);
			}
			
			
		} catch (Exception e) {
			
			LOGGER.error("Error while retriving the claim details using username in UserDAO Implementation");
			
		}finally {
			connection.close();
		}
		return showClaimDetailsList;
		
	}
	
	@Override
	public List<Questions> getQuestions(String policyType) throws Exception {
		
		List<Questions> questions = new ArrayList<>();
		connection = JDBCUtility.getConnection();
		
		try {
			LOGGER.info("Inside get questions method in AdminDAO Implementation");
			
			String query = QueryConstants.GET_QUESTIONS;
			statement = connection.prepareStatement(query);
			statement.setString(1, policyType);
			
			ResultSet res = statement.executeQuery();
			
			while(res.next()) {
				
				Questions question = new Questions();
				question.setQuestion(res.getString("question"));
				question.setQuestionId(res.getString("questionId"));
				questions.add(question);
				
			}
			
		} catch (Exception e) {

			LOGGER.error("Error while retriving the questions in AdminDAO Implementation");
			
		}
		
		return questions;
	}
	
	
	@Override
	public int addPolicyDetails(PolicyDetails policyDetails) throws Exception {
		
		connection = JDBCUtility.getConnection();
		int res=0;
		
		try {
			
			LOGGER.info("Inside the Add policy details method in AdminDAO Implementation");
			
			String query=QueryConstants.ADD_POLICY_DETAILS;
			
			statement = connection.prepareStatement(query);
			statement.setInt(1, policyDetails.getPolicyNumber());
			statement.setString(2, policyDetails.getQuestionId());
			statement.setString(3, policyDetails.getAnswer());
			statement.setInt(4, policyDetails.getClaimNumber());
			
			
			res = statement.executeUpdate();
			
		} catch (Exception e) {
			
			LOGGER.error("Error while inserting the data of policy details in AdminDAO Implementation");	
		}
		
		
		return res;
	}


	

}
