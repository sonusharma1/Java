package com.bank.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//importing all the annotation from lombok for getter,setter and constructor
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "customer") // creates table in database
public class Customer {

	// instance variable for customer entity
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;

	// creating columns for table customer
	@Column(name = "customer_name", nullable = false)
	private String customerName;

	@Column(name = "customer_address", nullable = false)
	private String customerAddress;

	@Column(name = "customer_panno", nullable = false)
	private String customerPanNo;

	@Column(name = "customer_adharno", nullable = false)
	private long customerAdharNo;

	@Column(name = "customer_phone", nullable = false)
	private long customerPhone;

	@Column(name = "customer_email", nullable = false)
	private String customerEmail;

	// this stores AccountDetail entity using one to one mapping
	// relationship
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JsonIgnore
	private AccountDetail accountDetail;

	@Builder
	public Customer(String customerName, String customerAddress, String customerPanNo, long customerAdharNo,
			long customerPhone, String customerEmail, AccountDetail accountDetail) {
		super();
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.customerPanNo = customerPanNo;
		this.customerAdharNo = customerAdharNo;
		this.customerPhone = customerPhone;
		this.customerEmail = customerEmail;
		this.accountDetail = accountDetail;
	}

}
