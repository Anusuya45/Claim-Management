package com.bo;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.dao.ClaimRepository;
import com.entity.Claim;


@Component
public class ClaimBO {
	
	@Autowired
	ClaimRepository reclaim;

	public Claim insertClaim(Claim addClaim) {
		return reclaim.save(addClaim);
		
	}
		
	public Claim findClaim(Long id) {
		Optional<Claim> clm = reclaim.findById(id);
		return clm.get();
		}
	
	public List<Claim> findAllClaim(){
		return reclaim.findAll();
	}
		
	public Claim updateClaim(Claim upClaim) {
			return reclaim.save(upClaim);	
	}

}
