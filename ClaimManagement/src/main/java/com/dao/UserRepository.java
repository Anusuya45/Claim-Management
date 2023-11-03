package com.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.entity.*;
@Repository

public interface UserRepository extends JpaRepository <User, Long> {
	 User findByUsername(String username);

}