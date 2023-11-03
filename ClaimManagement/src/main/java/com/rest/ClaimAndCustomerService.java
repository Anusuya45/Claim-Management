package com.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.entity.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dao.CustomerClaimCustomized;
import com.dao.CustomerCustomized;
import com.dao.UserRepository;
import com.dto.CustomerDTO;
import com.dto.LoginRequest;
import com.dto.LoginResponse;
import com.entity.Claim;
import com.entity.Customer;
import com.exception.CustomerNotFoundException;
import com.exception.InvalidCustomerException;
import com.response.ResponseObject;
import com.service.ClaimService;
import com.service.CustomerService;

@RestController
@CrossOrigin

public class ClaimAndCustomerService {
	@Autowired
	CustomerService service1;
	
	@Autowired
	ClaimService service2;
	
	@Autowired
    private UserRepository userRepository;
	
	Logger log = Logger.getLogger(ClaimAndCustomerService.class.getName());
	
	/*@RequestMapping("/sayHello")
	public String sayHello() {
		return "Say Hello";
		
	}*/
	 
	
	
	
		
	//insert customer	
	
	@RequestMapping(value="/createCustomer",method = RequestMethod.POST)	
	public ResponseObject addCustomer(@RequestBody CustomerDTO msg) {
		ResponseObject obj = new ResponseObject();
		try {
		System.out.println("Name:" + msg.getName());
		System.out.println("ContactNumber:" + msg.getContactNumber());
		System.out.println("Email:" + msg.getEmail());
		System.out.println("Street:" + msg.getStreet());
		Customer  c = new Customer();
		c.setName(msg.getName());
		c.setContactNumber( msg.getContactNumber());
		c.setEmail(msg.getEmail());
		c.setStreet( msg.getStreet());
		Customer c1 = service1.insertServiceCustomer(c);
		msg.setId(c1.getId());
		msg.setCreatedAt(c1.getCreatedAt());
		msg.setUpdatedAt(c1.getUpdatedAt());
		obj.setSuccessMessage("Customer added successfully");
		obj.setCustomerList(Collections.singletonList(msg));
		return obj;
		}catch(InvalidCustomerException e) {
			obj.setFailureMessage("Invalid customer data...Please enter a Name");
		}
		catch (Exception e) {
			obj.setFailureMessage("An error occurred while adding the customer");
		}
		return obj;
	}
	
	
	
	//fetch by id in customer table
	
	@RequestMapping(value="/fetchCustomerById",method = RequestMethod.GET)
	public ResponseObject fetchCustomer(@RequestParam long id) {
		ResponseObject obj = new ResponseObject();
		try {
		System.out.println("ID:" + id);
		Customer c = service1.findServiceCustomer(new Long(id));
		CustomerDTO dto = new CustomerDTO();
		dto.setCreatedAt(c.getCreatedAt());
		dto.setUpdatedAt(c.getUpdatedAt());
		dto.setName(c.getName());
		dto.setContactNumber(c.getContactNumber());
		dto.setStreet(c.getStreet());
		dto.setEmail(c.getEmail());
		dto.setId(c.getId());
		obj.setSuccessMessage("Customer fetched successfully");
		 obj.setCustomerList(Collections.singletonList(dto));
         return obj;
		}catch(CustomerNotFoundException e) {
			obj.setFailureMessage("Customer not found with ID: " + id);
		}
		catch (Exception e) {
			 obj.setFailureMessage("An error occurred while fetching the customer");
		       
		}
		 return obj;
	}
	
    //fetch All in Customer table
	
	@RequestMapping(value = "/fetchAllCustomers", method = RequestMethod.GET)
    public ResponseObject fetchAllCustomers() {
		ResponseObject obj = new ResponseObject();
       try {
    	   List<Customer> customers = service1.findAllServiceCustomer(); 
        List<CustomerDTO> dtos = new ArrayList<>();

        for (Customer c : customers) {
            CustomerDTO dto = new CustomerDTO();
            dto.setCreatedAt(c.getCreatedAt());
            dto.setUpdatedAt(c.getUpdatedAt());
            dto.setName(c.getName());
            dto.setContactNumber(c.getContactNumber());
            dto.setStreet(c.getStreet());
            dto.setEmail(c.getEmail());
            dto.setId(c.getId());
            dtos.add(dto);
        }
        obj.setSuccessMessage("Customers fetched successfully");
        obj.setCustomerList(dtos);
        return obj;
    }catch(CustomerNotFoundException e) {
		obj.setFailureMessage("Customers not found");
	}
       catch (Exception e) {
    	obj.setFailureMessage("An error occurred while fetching customers");
        
    }
       return obj;
	}

	
	//update customer
	
	@RequestMapping(value="/updateCustomer/{cid}",method = RequestMethod.POST)	
	public ResponseObject updateCustomer(@PathVariable("cid") long id,@RequestBody CustomerDTO msg) {
		ResponseObject obj = new ResponseObject();
		try {
		System.out.println("Name:" + msg.getName());
		System.out.println("ContactNumber:" + msg.getContactNumber());
		System.out.println("Email:" + msg.getEmail());
		System.out.println("Street:" + msg.getStreet());
		Customer c = service1.findServiceCustomer(id);
		c.setName(msg.getName());
		c.setContactNumber( msg.getContactNumber());
		c.setEmail(msg.getEmail());
		c.setStreet( msg.getStreet());
		Customer c1 = service1.updateServiceCustomer(c);
		CustomerDTO  c2 = new CustomerDTO();
		c2.setId(c1.getId());
        c2.setName(c1.getName());
       c2.setContactNumber(c1.getContactNumber());
       c2.setEmail(c1.getEmail());
       c2.setStreet(c1.getStreet());
       c2.setCreatedAt(c1.getCreatedAt());
       c2.setUpdatedAt(c1.getUpdatedAt());
       obj.setSuccessMessage("Customer updated successfully");
       obj.setCustomerList(Collections.singletonList(c2));
       return obj;
    }catch(InvalidCustomerException e) {
		obj.setFailureMessage("Please Enter a valid Email id");
	}catch (Exception e) {
    	obj.setFailureMessage("Given customer id does not exist");
       
      }
		 return obj;
	}
	
	
	//insert claim
	
	@RequestMapping(value="/createClaim",method = RequestMethod.POST)	
	public ResponseObject addClaim(@RequestBody Claim msg) {
		 ResponseObject obj = new ResponseObject();
		try {
		System.out.println("Claim Status:" + msg.getStatus());
		System.out.println("Description:" + msg.getDescription());
		System.out.println("Customer id:" + msg.getCustomer().getId());
		Customer c1 = service1.findServiceCustomer(msg.getCustomer().getId());
		if (c1 == null) {
            obj.setFailureMessage("Claim not found");
            System.out.println("Customer Response---->" + c1);
		}
		Claim  c = new Claim();
		c.setStatus(msg.getStatus());
		c.setDescription( msg.getDescription());
		Claim response = service2.insertServiceClaim(msg);
		obj.setSuccessMessage("Claim added successfully");
        obj.setClaimList(Collections.singletonList(response));
		System.out.println("Claim Added Successfully");
		return obj;
		}catch(Exception e) {
			obj.setFailureMessage("An error occurred while adding the claim");
			return obj;
		}
		
		
	}
	
	
	
	//fetch by id in claim table
	
	@RequestMapping(value = "/fetchClaimById", method = RequestMethod.GET)
	public ResponseObject fetchClaim(@RequestParam Long id) {
		ResponseObject obj = new ResponseObject();
	    try {
	        System.out.println("Claim ID: " + id);
	        
	        Claim c = service2.findServiceClaim(id);
	        
	        if (c == null) {
	            obj.setFailureMessage("Claim not found");
	            return obj;
	        }
	        
	        Claim extractedClaim = new Claim();
	        extractedClaim.setCreatedAt(c.getCreatedAt());
	        extractedClaim.setUpdatedAt(c.getUpdatedAt());
	        extractedClaim.setClaimId(c.getClaimId());
	        extractedClaim.setStatus(c.getStatus());
	        extractedClaim.setDescription(c.getDescription());
	        extractedClaim.setCustomer(c.getCustomer());
	       obj.setSuccessMessage("Claim fetched successfully");
	       obj.setClaimList(Collections.singletonList(extractedClaim));
	        
	        System.out.println("Claim fetched Successfully.....");
	        return obj;
	    } catch (Exception e) {
	        obj.setFailureMessage("An error occurred while fetching the claim");
	        return obj;
	    }
	}
	
	
	//fetch all in claim table

	@RequestMapping(value = "/fetchAllClaims", method = RequestMethod.GET)
	public ResponseObject fetchAllClaims() {
		 ResponseObject obj = new ResponseObject();
	    try {
	        List<Claim> fetchAll = service2.findAllServiceClaim();
	       
	        if (fetchAll.isEmpty()) {
	            obj.setFailureMessage("No claims found");
	            return obj;
	        }
	        obj.setSuccessMessage("Claims fetched successfully");
	        obj.setClaimList(fetchAll);
	        return obj;
	    } catch (Exception e) {
	        obj.setFailureMessage("An error occurred while fetching claims");
	        return obj;
	    }
	}




	
	
	//update claim
	
	@RequestMapping(value = "/updateClaim/{cid}", method = RequestMethod.POST)
	public ResponseObject updateClaim(@PathVariable("cid") long id, @RequestBody Claim updatedClaim) {
		 ResponseObject obj= new ResponseObject();
		try {
	    System.out.println("Status: " + updatedClaim.getStatus());
	   // System.out.println("Description: " + updatedClaim.getDescription());
	    //System.out.println("Customer: " + updatedClaim.getCustomer());
	    Claim c = service2.findServiceClaim(id);
	    if (c == null) {
            obj.setFailureMessage("Claim not found");
            return obj;
	    }
	    c.setStatus(updatedClaim.getStatus());
	   //Claim1.setCustomer(updatedClaim.getCustomer());
	    //Claim1.setDescription(updatedClaim.getDescription());
	    Claim result = service2.updateServiceClaim(c);
	    obj.setSuccessMessage("Claim updated successfully");
        obj.setClaimList(Collections.singletonList(result));
	    System.out.println("Claim updated successfully.....");
	    return obj;
		} catch (Exception e) {
			obj.setFailureMessage("An error occurred while updating the claim");
			return obj;
		}
	    
	}
	
	//custom queries
	
	//>id query
	
	 @RequestMapping(value = "/greaterThanGivenId", method = RequestMethod.GET)
	    public ResponseObject getCustomersById(@RequestParam Long id) {
		 ResponseObject obj= new ResponseObject();
	        try{
	        	List<Customer> greaterThan = service1.findServiceCustomersById(id);
	        	if (greaterThan.isEmpty()) {
	                obj.setFailureMessage("No customers found with ID greater than " + id);
	                return obj;
	            }
	        	obj.setSuccessMessage("Customers fetched successfully");
	            obj.setCusList(greaterThan);
	            return obj;
	        }catch (Exception e) {
	        	 obj.setFailureMessage("An error occurred while fetching customers");
	             return obj;
	        }
	        
	 }
	    
	 
	 //using like operator to find by name or character
	 
	 @RequestMapping(value = "/findByNameOrCharacter", method = RequestMethod.GET)
	    public List<Customer> getCustomersByName(@RequestParam String name) {
	        List<Customer> charname = service1.findByServiceNames(name);  
	        return charname;
   }
	 
	 
	 //using aggregate function - count
	 
	 @RequestMapping(value = "/totalNoOfCustomers", method = RequestMethod.GET)
	    public Long getTotalNumberOfCustomers() {
	        long totalCustomers = service1.findTotalServiceNumbersOfCustomers();
	        return totalCustomers;
	    }
	 
	 //using logical operator AND 
	 
	 @RequestMapping(value = "/findByEmailAndStreet", method = RequestMethod.GET)
	    public List<Customer> getCustomersByEmailAndStreet(@RequestParam("email") String email, @RequestParam("street") String streetName ) {
	        List<Customer> emailStreet = service1.findCustomersServiceByEmailsAndStreet(email, streetName);
	        return emailStreet;
   }
	 
	 //fetch few columns by name
	 
	 @RequestMapping(value = "/fetchFewBycustomerName", method = RequestMethod.GET)
	    public List<CustomerCustomized> getFewColumnCustomerByName(@RequestParam String name) {
	        List<CustomerCustomized> fewCustomers = service1.findByServiceCustomerNames(name);
	        return fewCustomers;
   }
	 
	 //Named queries
	 
	 //order by name in descending order
	 
	 @RequestMapping(value = "/orderByNameDescending", method = RequestMethod.GET)
	    public List<Customer> getAllCustomersOrderByNameDescending() {
	        List<Customer> orderBy = service1.findAllOrderByNamesServiceDescending();
	        return orderBy;
  }
	 
	 // find by email
	 
	 @RequestMapping(value = "/findByEmail", method = RequestMethod.GET)
	    public List<Customer> getCustomersByEmail(@RequestParam String email) {
	        List<Customer> customerEmail = service1.findByServiceEmails(email);
	        return customerEmail; 
	 }
	 
	 //inner join
	 
	 @RequestMapping(value = "/innerJoincustomers", method = RequestMethod.GET)
	    public List<Customer> getCustomersWithClaims() {
	        List<Customer> joinCustomers = service1.findCustomerServiceWithClaims();
	        return joinCustomers;
	 }
	 
	 //left outer join
	 
	 @RequestMapping(value = "/leftOuterJoincustomers", method = RequestMethod.GET)
	    public List<Customer> getCustomersWithClaimLeft() {
	        List<Customer> leftOuter = service1.findCustomerServiceWithClaimLeft();
	        return leftOuter;
	 }
	 
	 //customized data by join
	 
	 @RequestMapping(value = "/customizedCustomerClaim", method = RequestMethod.GET)
	    public List<CustomerClaimCustomized> getCustomersWithClaim() {
	        List<CustomerClaimCustomized> customerCustomized = service1.findByCustomerClaim();
	        return customerCustomized;
	 }
	 
	 @RequestMapping(value = "/login", method = RequestMethod.POST)
	    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest request) {
	        Map<String, Object> response = new HashMap<>();

	        User user = userRepository.findByUsername(request.getUsername());

	        if (user != null && user.getPassword().equals(request.getPassword())) {
	            response.put("success", true);
	            response.put("message", "Login successful");
	            return ResponseEntity.ok(response);
	        } else {
	            response.put("success", false);
	            response.put("message", "Invalid credentials");
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	        }
	    }
	}

	 
	



