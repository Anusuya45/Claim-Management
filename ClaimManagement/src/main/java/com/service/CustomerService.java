package com.service;

import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.bo.ClaimBO;
import com.bo.CustomerBO;
import com.dao.CustomerClaimCustomized;
import com.dao.CustomerCustomized;
import com.entity.Claim;
import com.entity.Customer;
import com.exception.CustomerNotFoundException;
import com.exception.InvalidCustomerException;

@Component

public class CustomerService {
	@Autowired
	CustomerBO serviceBO; 
	
	@Autowired
	ClaimBO  serviceClaimBO;
	
	
	Logger log = Logger.getLogger(CustomerService.class.getName());
	
	
	public List<Customer> findServiceCustomersById(Long id1){
		return serviceBO.findCustomersById(id1);
	}
	
	public List<Customer> findByServiceNames(String name1){
		return serviceBO.findByNames(name1);
	}
	
	public List<CustomerCustomized> findByServiceCustomerNames (String name1){
		return serviceBO.findByCustomerNames(name1);
	}
	
	public long findTotalServiceNumbersOfCustomers() {
		return serviceBO.findTotalNumbersOfCustomers();
	}
	
	public List<Customer> findCustomersServiceByEmailsAndStreet(String email1,String streetName){
		return serviceBO.findCustomersByEmailsAndStreet(email1,streetName);
	}
	
	
	public List<Customer> findAllOrderByNamesServiceDescending(){
		return serviceBO.findAllOrderByNamesDescending();
	}
	
	public List<Customer>  findByServiceEmails(String email1){
		return serviceBO.findByEmails(email1);
	}
	
	public List<Customer>  findCustomerServiceWithClaims(){
		return serviceBO. findCustomerWithClaims();
	}
	
	public List<Customer> findCustomerServiceWithClaimLeft(){
		return serviceBO.findCustomersWithClaimLeft();
	}
	
	public List<CustomerClaimCustomized>  findByCustomerClaim(){
		return serviceBO. findByCustomerClaim();
	}
	
	
	
	
	public Customer insertServiceCustomer(Customer addCustomer) throws InvalidCustomerException {
		log.info("Add method triggered..");
		try {
		log.info("Record Added Successfully");
		return serviceBO.insertCustomer(addCustomer);
		
		}catch ( InvalidCustomerException e) {
			log.error("Error when adding customer details",e);
			throw new  InvalidCustomerException("Invalid data");
		}
	}
	
	public Customer findServiceCustomer(Long id) throws CustomerNotFoundException {
		log.info("Fetch by ID method triggered..");
		try{
			log.info("Customer Id fetched  Successfully");
			return  serviceBO.findCustomer(id);
		}catch (CustomerNotFoundException e) {
			log.error("Error when fetching customer id",e);
			throw new CustomerNotFoundException("Customer data not found");
		}
		
		}
	
	public List<Customer> findAllServiceCustomer() throws CustomerNotFoundException{
		log.info("FetchAll method triggered..");
		try {
			
			log.info("Customer details fetched Successfully");
		return serviceBO.findAllCustomer();

	}catch ( IllegalArgumentException e) {
		log.error("Error when fetching customer details",e);
		throw new CustomerNotFoundException("Customer data not found");
	}
	}
		
	public Customer updateServiceCustomer(Customer upCustomer) throws InvalidCustomerException {
		log.info("Update method triggered..");
		try {
			log.info("Customer details updated Successfully");
			return serviceBO.updateCustomer(upCustomer);	
	}catch ( IllegalArgumentException e) {
		log.error("Error when updating customer details" ,e);
		throw new  InvalidCustomerException("Invalid data");
	}
}
	
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void transactionClaimManagement(Customer c,Claim cl) throws InvalidCustomerException {
		System.out.println("Customer Add-----Before");
		serviceBO.insertCustomer(c);
		System.out.println("Customer Add-----After");
		System.out.println("Claim Add-----Before");
		serviceClaimBO.insertClaim(cl);
		System.out.println("Claim Add-----After");
	}
		
	

}
