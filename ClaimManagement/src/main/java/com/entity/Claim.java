package com.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class Claim {
	
	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long claimId;
     
     @Column
     private String description;
     
    
     @Column(name="claim_status")
     private String status;
     
     
     @Column(nullable = false,updatable = false)
     @Temporal(TemporalType.TIMESTAMP)
     @CreatedDate
     private Date createdAt;
     
     @Column(nullable = false)
     @Temporal(TemporalType.TIMESTAMP)
     @LastModifiedDate
     private Date updatedAt;
     
     
     @JsonBackReference
     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "customerid",nullable = false,referencedColumnName = "id")
     Customer customer;
     
     

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

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

	@Override
	public String toString() {
		return "Claim [claimId=" + claimId + ", description=" + description + ", status="
				+ status + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
     
     
      
}
