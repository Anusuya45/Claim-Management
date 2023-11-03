package com.bo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dao.CustomerClaimCustomized;
import com.dao.CustomerCustomized;
import com.dao.CustomerRepository;
import com.entity.Customer;
import com.exception.CustomerNotFoundException;
import com.exception.InvalidCustomerException;

@Component
public class CustomerBO {
	@Autowired
	CustomerRepository recustomer;
	
	public List<Customer> findCustomersById(Long id1){
		return recustomer.findCustomerById(id1);
	}
	
	public List<Customer> findByNames(String name1){
		return recustomer.findByName(name1);
	}
	
	public List<CustomerCustomized> findByCustomerNames (String name1){
		return recustomer.findByCustomerName(name1);
	}
	
	public long findTotalNumbersOfCustomers() {
		return recustomer.findTotalNumberOfCustomers();
	}
	
    public List<Customer> findCustomersByEmailsAndStreet(String email1,String streetName){
    	return recustomer.findCustomersByEmailAndStreet( email1,streetName);
	}

	
	public List<Customer> findAllOrderByNamesDescending(){
		return recustomer.findAllOrderByNameDescending();
	}
	
	public  List<Customer> findByEmails(String email1){
		return recustomer.findByEmail (email1);
	}

	public List<Customer> findCustomerWithClaims(){
		return recustomer.findCustomerWithClaim();
	}
	 
	public List<Customer> findCustomersWithClaimLeft(){
		return recustomer.findCustomerWithClaimLeft();
	}
	
	public List<CustomerClaimCustomized>  findByCustomerClaim(){
		return recustomer.findByCustomerClaim();
	}
	
	
	public Customer insertCustomer(Customer addCustomer) throws InvalidCustomerException {
		if (addCustomer.getName() == null || addCustomer.getName().isEmpty()) {
            throw new InvalidCustomerException("Invalid customer data: Please enter a Name");
        }
		return recustomer.save(addCustomer);
		
	}
		
	public Customer findCustomer(Long id) throws CustomerNotFoundException {
		Optional<Customer> custom = recustomer.findById(id);
		
		if (id == 0) {
	        throw new CustomerNotFoundException("Customer not found with ID: " + id);
	    }
		return custom.get();
		
	}
	
	public List<Customer> findAllCustomer() throws CustomerNotFoundException{
	 
	        if(recustomer.findAll().isEmpty()) {
	        	throw new CustomerNotFoundException("Customers not found");
	        }
	        return recustomer.findAll();
	}
	public Customer updateCustomer(Customer upCustomer)throws InvalidCustomerException{
		
		if (!upCustomer.getEmail().contains("@gmail.com")) {
            throw new InvalidCustomerException("Invalid email format");
        }
			return recustomer.save(upCustomer);	
	}
	
}


