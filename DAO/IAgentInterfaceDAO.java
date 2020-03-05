package com.cg.DAO;

import java.util.List;

import com.cg.dto.Claim;
import com.cg.dto.Policy;
import com.cg.dto.PolicyDetails;
import com.cg.dto.Questions;
import com.cg.dto.ShowClaimDetails;

public interface IAgentInterfaceDAO {
	
	public List<Policy> getclaimDetails(String agentUserName) throws Exception;
	
	public List<Policy> getclaimDetails(String agentUserName,String clientUserName) throws Exception;
	
	public int registerClaim(Claim claim) throws Exception;
	
	List<ShowClaimDetails> getClaimDetails(String username,String filter,int accountNumber) throws Exception;

	List<ShowClaimDetails> getClaimDetails(String username) throws Exception;
	
}
