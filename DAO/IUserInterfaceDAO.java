package com.cg.DAO;

import java.util.List;

import com.cg.dto.Claim;
import com.cg.dto.Policy;
import com.cg.dto.PolicyDetails;
import com.cg.dto.Questions;
import com.cg.dto.ShowClaimDetails;

public interface IUserInterfaceDAO {
	public List<Policy> getclaimDetails(String username) throws Exception;
	
	public int registerClaim(Claim claim) throws Exception;
	
	List<ShowClaimDetails> getClaimDetails(String username) throws Exception;
	
	List<ShowClaimDetails> getClaimDetails(String username,String filter) throws Exception;
	
	List<Questions> getQuestions(String policyType) throws Exception;
	
	public int addPolicyDetails(PolicyDetails policyDetails) throws Exception;
	
	
}
