package com.cg.DAO;

import java.util.ArrayList;
import java.util.List;

import com.cg.dto.Claim;
import com.cg.dto.UserResponses;
import com.cg.exceptions.InsuranceException;

public interface IGetClaimDetails {
	public Claim getClaim(int claimNumber) throws Exception;
	public List<UserResponses> getResponses(int claimNumber) throws InsuranceException;
}
