package com.bank.model;

import java.time.LocalDate;

import com.bank.entity.AccountDetail;

import lombok.AllArgsConstructor;
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
public class BankTransactionsDTO {

	// copy of instance variable created 
	private int transactionId;

	private LocalDate dateOfTransaction;

	private String transactionType;

	private double transactionAmount;

	private String descriptionOfTransaction;

	private AccountDetail accountDetail;
}
