package com.cg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor.STRING;
import javax.servlet.RequestDispatcher;

import org.apache.log4j.Logger;

import com.cg.dto.Claim;
import com.cg.dto.Policy;
import com.cg.dto.PolicyDetails;
import com.cg.dto.Questions;
import com.cg.dto.ShowClaimDetails;
import com.cg.queries.QueryConstants;
import com.cg.utility.JDBCUtility;

public class AgentInterfaceDAOImpl implements IAgentInterfaceDAO{

	Connection connection = null;
	PreparedStatement statement = null;
	
	static final Logger LOGGER = Logger.getLogger(AdminInterfaceDAOImpl.class);
	@Override
	public List<Policy> getclaimDetails(String agentUserName) throws Exception {
		List<Policy> listOfPolicies = new ArrayList<Policy>();
		connection = JDBCUtility.getConnection();
	
		try {
			LOGGER.info("Inside get claim details in AgentDAO Implementation");
			
			String queryAccountNum = QueryConstants.GET_ACCOUNTNUMBER_LIST_IN_AGENT;
			statement=connection.prepareStatement(queryAccountNum);
			statement.setString(1, agentUserName);
			
			ResultSet res = statement.executeQuery();
			List<Integer> accountNumber = new ArrayList<>();
			while(res.next()) {
				accountNumber.add(res.getInt("accountNumber"));
			}
			
			int i = 0;
			
			while (i < accountNumber.size()) {
				
				String query = QueryConstants.GET_CLAIM_DETAILS_USING_ACCOUNTNUMBER;
				statement = connection.prepareStatement(query);
				statement.setInt(1, accountNumber.get(i));
				res = statement.executeQuery();
				
				while (res.next()) {
					
					Policy policy = new Policy();
					
					policy.setPolicyNumber(res.getInt("policyNumber"));
					policy.setPolicyPremium(res.getInt("policyPremium"));
					policy.setAccountNumber(res.getInt("accountNumber"));
					policy.setPolicyType(res.getString("policyType"));
					listOfPolicies.add(policy);
					
				} 
				i++;
			}
			
		}catch(Exception e) {
			
			LOGGER.error("Error while retriving the list of policies in AgentDAO Implementation");
			
		}finally {
			connection.close();
		}
		return listOfPolicies;

	}
	
	
	@Override
	public List<Policy> getclaimDetails(String agentUserName,String clientUserName) throws Exception {
		
		List<Policy> listOfPolicies = new ArrayList<Policy>();
		connection = JDBCUtility.getConnection();
	
		try {
			LOGGER.info("Inside the get claim details method using Client username in AgentDAO Implementation");
			
			String queryAccountNum = QueryConstants.GET_ACCOUNTNUMBER_LIST_IN_AGENT_USING_USERNAME;
			statement=connection.prepareStatement(queryAccountNum);
			statement.setString(1, clientUserName);
			statement.setString(2, agentUserName);
			
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
			
			LOGGER.error("Error while retriving the policy details using username in AgentDAO Implementation");
			
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
			LOGGER.info("Inside the register claim method in AgentDAO Implementation");
			
			String query = QueryConstants.ADD_CLAIM;
			
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
			
			LOGGER.error("Error while registering the claim for user in AgentDAO Implementation");
		} finally {
			connection.commit();
			connection.close();
		}
		
		return res;
	}
	
	
	@Override
	public List<ShowClaimDetails> getClaimDetails(String username, String filter,int accountNumber) throws Exception {
		List<ShowClaimDetails> showClaimDetailsList = new ArrayList<ShowClaimDetails>();
		connection = JDBCUtility.getConnection();
		
		try {
			LOGGER.info("Inside the get claim details method using filter in AgentDAO Implementation");
			
			String query="";
			ResultSet res=null;
			
		
			System.out.println("username "+username);
			if(accountNumber!=0) {
				if(filter.equals("all")) {
						System.out.println("acc"+accountNumber);
						query = QueryConstants.DISPLAY_CLAIM_DETAILS_AGENT;
						statement = connection.prepareStatement(query);
						statement.setInt(1, accountNumber);
						
					
				}else {
					
					query = QueryConstants.DISPLAY_CLAIM_DETAILS_USING_STATUS;
					
					statement = connection.prepareStatement(query);
					statement.setInt(1, accountNumber);
					statement.setString(2, filter);
					
				}
				res = statement.executeQuery();
				
				while (res.next()) {
					
					ShowClaimDetails claimDetails = new ShowClaimDetails();
					
					claimDetails.setAccountNumber(res.getInt("accountNumber"));
					claimDetails.setPolicyNumber(res.getInt("policyNumber"));
					claimDetails.setPolicyPremium(res.getInt("policyPremium"));
					claimDetails.setPolicyType(res.getString("policyType"));
					claimDetails.setClaimNumber(res.getInt("claimNumber"));
					claimDetails.setStatus(res.getString("status"));
					showClaimDetailsList.add(claimDetails);
				}
			}else {
				List<Integer> accountNumberList = new ArrayList<>();
				if(filter.equals("all")) {
					
					String accNum = "select accountnumber from userrole where agentid=?";
					statement = connection.prepareStatement(accNum);
					statement.setString(1, username);
					
					ResultSet accSet = statement.executeQuery();
					while(accSet.next()) {
						accountNumberList.add(accSet.getInt("accountNumber"));
					}
					
					System.out.println("AccList = "+accountNumberList);
					
					for (Integer list : accountNumberList) {
						
						query = QueryConstants.DISPLAY_CLAIM_DETAILS_ALL_AGENT;
						statement = connection.prepareStatement(query);
						
						statement.setInt(1, list);
						ResultSet searchSet = statement.executeQuery();
						while(searchSet.next()) {
							ShowClaimDetails claimDetails = new ShowClaimDetails();
							
							claimDetails.setAccountNumber(searchSet.getInt("accountNumber"));
							claimDetails.setPolicyNumber(searchSet.getInt("policyNumber"));
							claimDetails.setPolicyPremium(searchSet.getInt("policyPremium"));
							claimDetails.setPolicyType(searchSet.getString("policyType"));
							claimDetails.setClaimNumber(searchSet.getInt("claimNumber"));
							claimDetails.setStatus(searchSet.getString("status"));
							showClaimDetailsList.add(claimDetails);
						}
						
						
					}
					
				}else {
					
					String accNum = "select accountnumber from userrole where agentid=?";
					statement = connection.prepareStatement(accNum);
					statement.setString(1, username);
					
					ResultSet accSet = statement.executeQuery();
					while(accSet.next()) {
						accountNumberList.add(accSet.getInt("accountNumber"));
					}
					
					
					for (Integer list : accountNumberList) {
						query = QueryConstants.DISPLAY_CLAIM_DETAILS_WITHOUT_ACCOUNTNUMBER_AGENT;
						statement = connection.prepareStatement(query);
						statement.setInt(1, list);
						statement.setString(2, filter);
						
						ResultSet searchSet = statement.executeQuery();
						while(searchSet.next()) {
							ShowClaimDetails claimDetails = new ShowClaimDetails();
							
							claimDetails.setAccountNumber(searchSet.getInt("accountNumber"));
							claimDetails.setPolicyNumber(searchSet.getInt("policyNumber"));
							claimDetails.setPolicyPremium(searchSet.getInt("policyPremium"));
							claimDetails.setPolicyType(searchSet.getString("policyType"));
							claimDetails.setClaimNumber(searchSet.getInt("claimNumber"));
							claimDetails.setStatus(searchSet.getString("status"));
							showClaimDetailsList.add(claimDetails);
						}
						
						
					}
					
					
					
					
					
					
					
					
					
					
					
					
				}
				
			}
			
			
		} catch (Exception e) {
			
			LOGGER.error("Errro while retriving the claim details using filter in agentDAO Implements");
			
		}
		
		return showClaimDetailsList;
	}
	
	
	@Override
	public List<ShowClaimDetails> getClaimDetails(String username) throws Exception {
		List<ShowClaimDetails> showClaimDetailsList = new ArrayList<ShowClaimDetails>();
		connection = JDBCUtility.getConnection();
		
		try {
			LOGGER.info("Inside the get claim details method using username in AgentDAO Implemetation");
			
			String queryAccountNum = QueryConstants.GET_ACCOUNTNUMBER_LIST_IN_AGENT;
			statement=connection.prepareStatement(queryAccountNum);
			statement.setString(1, username);
			
			ResultSet res = statement.executeQuery();
			List<Integer> accountNumber = new ArrayList<>();
			while(res.next()) {
				accountNumber.add(res.getInt("accountNumber"));
			}
			
			int i = 0;
			
			
			while (i < accountNumber.size()) {
				
				String query = QueryConstants.DISPLAY_CLAIM_DETAILS;
				statement = connection.prepareStatement(query);
				statement.setInt(1, accountNumber.get(i));
				res = statement.executeQuery();
				
				while (res.next()) {
					
					ShowClaimDetails claimDetails = new ShowClaimDetails();
					
					claimDetails.setAccountNumber(res.getInt("accountNumber"));
					claimDetails.setPolicyNumber(res.getInt("policyNumber"));
					claimDetails.setPolicyPremium(res.getInt("policyPremium"));
					claimDetails.setPolicyType(res.getString("policyType"));
					claimDetails.setClaimNumber(res.getInt("claimNumber"));
					claimDetails.setStatus(res.getString("status"));
					showClaimDetailsList.add(claimDetails);
				} 
				i++;
			}
			
		}catch(Exception e) {
			LOGGER.error("Error while retriving the claim details in AgentDAO Implementation");
		}finally {
			connection.close();
		}
		return showClaimDetailsList;
	}
	
	
	
}
