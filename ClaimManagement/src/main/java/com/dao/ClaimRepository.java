package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.entity.*;

@Repository
public interface ClaimRepository extends JpaRepository <Claim,Long>{

}
