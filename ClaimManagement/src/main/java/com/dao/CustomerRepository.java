package com.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.entity.Customer;

@Repository

public interface CustomerRepository extends JpaRepository<Customer,Long> {
	
	//Queries to fetch all columns based on a condition
	
     @Query(value="select c from Customer c where c.id> :id1")
     List<Customer> findCustomerById(@Param("id1")Long id);
      
    
     @Query(value = "SELECT c FROM Customer c WHERE c.name LIKE %:name1%")
     List<Customer> findByName(@Param("name1") String name);
     
     @Query("SELECT COUNT(c) FROM Customer c")
     long findTotalNumberOfCustomers();

     @Query("SELECT c FROM Customer c WHERE c.email = :email1 AND c.street = :streetName")
     List<Customer> findCustomersByEmailAndStreet(@Param("email1") String email1, @Param("streetName") String streetName);

     
     //Query to fetch only few columns
     
     @Query("select c.name as name,c.street as street from Customer c where c.name=:name1")
     List<CustomerCustomized> findByCustomerName(@Param("name1") String name);
     
     
     
     //Named Query
     
     List<Customer> findAllOrderByNameDescending();
     
     List<Customer> findByEmail(String email1);
     
     //Inner Join
     
    
     @Query("SELECT c FROM Customer c JOIN c.claim a")
     List<Customer> findCustomerWithClaim();
    
     
     //Left Outer Join
     
    
     @Query("SELECT c FROM Customer c LEFT JOIN c.claim a")
     List<Customer> findCustomerWithClaimLeft();
     
     //Customized data by join
     
   
     @Query("SELECT c.name as name, c.street as street, c.email as email FROM Customer c JOIN c.claim a")
     List<CustomerClaimCustomized> findByCustomerClaim();
     
}
