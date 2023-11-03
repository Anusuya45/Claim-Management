package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bo.ClaimBO;
import com.entity.Claim;

@Component
public class ClaimService {
	
	@Autowired
	ClaimBO servicesBO;
	
	public Claim insertServiceClaim(Claim addClaim) {
		return servicesBO.insertClaim(addClaim);
	}
	
	public Claim findServiceClaim(Long id) {
		return  servicesBO.findClaim(id);
		
		}
	
	public List<Claim> findAllServiceClaim(){
		return servicesBO.findAllClaim();
	}
		
	public Claim updateServiceClaim(Claim upClaim) {
			return servicesBO.updateClaim(upClaim);	
	}

}
