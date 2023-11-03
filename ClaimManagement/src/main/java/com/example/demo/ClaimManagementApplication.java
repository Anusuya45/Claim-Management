package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.entity.Customer;
import com.service.CustomerService;
import com.dao.CustomerClaimCustomized;
import com.dao.CustomerCustomized;
import com.entity.Claim;
import com.service.ClaimService;


@SpringBootApplication
@EnableJpaAuditing
@ComponentScan("com")
@EntityScan(basePackages = {"com.entity"})
@EnableJpaRepositories("com.dao")


public class ClaimManagementApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ClaimManagementApplication.class, args);
		
		//task 1 insert customer
		CustomerService service = ctx.getBean(CustomerService.class);
		/*Customer customerobj = new Customer();
		customerobj.setName("Mikey");
		customerobj.setEmail("Sano@gmail.com");
		customerobj.setStreet("Tokyo Revengers Street");
		customerobj.setContactNumber(8976567856l);*/
		
	    //service.insertServiceCustomer(customerobj);
		
		//task 2 insert claim
		/*ClaimService serviceClaim = ctx.getBean(ClaimService.class);
		Claim claimrobj = new Claim();
		claimrobj.setStatus("Rejected");
		claimrobj.setDescription("Property Related");
		 
		Customer custom = new Customer();
		custom.setId(1l);
		claimrobj.setCustomer(custom);
		serviceClaim.insertServiceClaim(claimrobj);*/
		
		//task 3 insert claim
		/*Claim c1 = new Claim();
		c1.setStatus("Accepted");
		c1.setDescription("Accident");
		
		Claim c2 = new Claim();
		c2.setStatus("InProgess");
		c2.setDescription("theft");
		
		//insert Customer
		Customer cu1 = new Customer();
		cu1.setContactNumber(9856407865l);
		cu1.setName("Taehyung");
		cu1.setStreet("Bangtan street");
		cu1.setEmail("taetae@gmail.com");
		
		//Associating Claim to Customer
		c1.setCustomer(cu1);
		c2.setCustomer(cu1);
		
		//Associating Customer with Claim
		List<Claim> cu2 = new ArrayList<Claim>();
		cu2.add(c1);
		cu2.add(c2);
		cu1.setClaim(cu2);
		
		System.out.println("Adding Customer.......");
		CustomerService service3 = ctx.getBean(CustomerService.class);
		service3.insertServiceCustomer(cu1);*/
		
		
		//fetch
		//System.out.println(service.findServiceCustomer(2l));
		
		//fetchAll
		//System.out.println(service.findAllServiceCustomer());
		
		
		//update
		//customerobj.setId(2);
		//customerobj.setEmail("tanjiro@gmail.com");
		//service.updateServiceCustomer(customerobj);
		
		
		//Join process
		
		/*Customer cs = service.findServiceCustomer(3);
		System.out.println("Customer:" + cs);
		
		List<Claim> claimList = cs.getClaim();
		System.out.println("*************Printing Claims***************");
		for(Claim cobj : claimList) {
			System.out.println("Claim Description :" + cobj.getDescription() );
		}*/
		
		//Transaction
		
		/*CustomerService tsc  = ctx.getBean(CustomerService.class);
		Claim clm1 = new Claim();
		clm1.setStatus("Revieweed");
		clm1.setDescription("Health");
		
		Customer cust1 = new Customer();
		cust1.setId(3l);
		clm1.setCustomer(cust1);
		
		Customer cust2 = new Customer();
		cust2.setContactNumber(9043245907l);
		cust2.setName("buzan");
		cust2.setStreet("Slayers Street");
		cust2.setEmail("buzan@gmail.com");
		
		tsc.transactionClaimManagement(cust2, clm1);*/
		
		//Custom Queries
		
		//List<Customer> list = service.findServiceCustomersById(1l);
		//System.out.println("Customer Based on Id------>" + list);
		
		//List<Customer> listName = service.findByServiceNames("a");
		//System.out.println("Customer Based on Name------>" + listName);
		
		/*List<CustomerCustomized> customerList = service.findByServiceCustomerNames("Gojo");
		for(CustomerCustomized cl : customerList) {
			System.out.println("Customer Customized Data------>" + cl.getName() + "-" + cl.getStreet());
		}*/
		
		/*long numberList = service.findTotalServiceNumbersOfCustomers();
		System.out.println("Total number of Customers ------>" + numberList);*/
		
		//List<Customer> Streetlist = service.findCustomersServiceByEmailsAndStreet("taetae@gmail.com","Bangtan street");
		//System.out.println("Customer Based on Email and Street------>" + Streetlist );
		
		
		
		
		// Named Query
		
		//System.out.println("Customer Data OrderBy Name ------>" + service.findAllOrderByNamesServiceDescending());
		
		//System.out.println("Customer Data by Email-------->" + service.findByServiceEmails("Sano@gmail.com"));
		
		//Inner Join
		
		//List<Customer> customerList = service.findCustomerServiceWithClaims();
		//System.out.println("Customer with Claim INNER JOIN" + customerList );
		
		//Left Outer Join
		
		//List<Customer> customerList1 = service.findCustomerServiceWithClaimLeft();
		//System.out.println("Customer with Claim Left Outer Join------> "+  customerList1);
		
		//Customized join
		
		/*List<CustomerClaimCustomized> customerClaimList = service.findByCustomerClaim();
		for(CustomerClaimCustomized culm : customerClaimList) {
			System.out.println("Customized Joined Data By using Joins------>" + culm.getName()+"-" +culm.getStreet()+"-"+ culm.getEmail());
		}*/
		
		}

}
