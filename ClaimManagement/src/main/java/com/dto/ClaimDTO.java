package com.dto;

import java.io.Serializable;
import java.util.Date;

import com.entity.Customer;

public class ClaimDTO implements Serializable {

	 private Long claimId;
	 private String description;
	 private String status;
	 private Date createdAt;
	 private Date updatedAt;
	 Customer customer;
	public Long getClaimId() {
		return claimId;
	}
	public void setClaimId(Long claimId) {
		this.claimId = claimId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "ClaimDTO [claimId=" + claimId + ", description=" + description + ", status=" + status + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + ", customer=" + customer + "]";
	}
	 
	 
	 
}
