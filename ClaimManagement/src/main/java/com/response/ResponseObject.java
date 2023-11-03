package com.response;

import java.util.List;

import com.dto.CustomerDTO;
import com.entity.Claim;
import com.entity.Customer;

public class ResponseObject{
	String successMessage;
	String failureMessage;
	List<Claim> claimList;
	List<Customer> cusList;
	List<CustomerDTO> customerList;
	
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	public String getFailureMessage() {
		return failureMessage;
	}
	public void setFailureMessage(String failureMessage) {
		this.failureMessage = failureMessage;
	}
	public List<Claim> getClaimList() {
		return claimList;
	}
	public void setClaimList(List<Claim> claimList) {
		this.claimList = claimList;
	}
	public List<CustomerDTO> getCustomerList() {
		return customerList;
	}
	public void setCustomerList(List<CustomerDTO> customerList) {
		this.customerList = customerList;
	}
	public List<Customer> getCusList() {
		return cusList;
	}
	public void setCusList(List<Customer> cusList) {
		this.cusList = cusList;
	}
	
	
	
	
	
	
	
	
}
