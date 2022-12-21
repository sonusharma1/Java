package com.bank.model;

import java.time.LocalDate;
import java.util.List;

import com.bank.entity.BankTransactions;

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
public class AccountDetailDTO { // DTO class to store object

	// copy of instance variable created 
	private int accountId;

	private long accountNumber;

	private LocalDate openDate;

	private String accountType;

	private double accountBalance;

	private List<BankTransactions> bankTransactions;
}
