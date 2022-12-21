package com.bank.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// importing all the annotation from lombok for getter,setter and constructor
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "bank_transactions") // creates table in database
public class BankTransactions {

	// instance variable for BankTransactions entity
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;

	// creating columns for table bank_transactions
	@Column(name = "date_of_transaction")
	private LocalDate dateOfTransaction;

	@Column(name = "transaction_type")
	private String transactionType;

	@Column(name = "transaction_amount")
	private double transactionAmount;

	@Column(name = "description_of_transaction")
	private String descriptionOfTransaction;

	// this stores AccountDetail entity using many to one mapping
	// relationship

	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private AccountDetail accountDetail;

	@Builder
	public BankTransactions(LocalDate dateOfTransaction, String transactionType, double transactionAmount,
			String descriptionOfTransaction) {
		super();
		this.dateOfTransaction = dateOfTransaction;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
		this.descriptionOfTransaction = descriptionOfTransaction;
	}

}
