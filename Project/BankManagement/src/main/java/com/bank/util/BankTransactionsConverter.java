package com.bank.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.bank.entity.BankTransactions;
import com.bank.model.BankTransactionsDTO;

@Component
public class BankTransactionsConverter {

	// convert BankTransactionsDTO to Entity(BankTransactions)
	public BankTransactions convertToEntity(BankTransactionsDTO bankTransactionsDTO) {

		BankTransactions bankTransactions = new BankTransactions();
		if (bankTransactionsDTO != null) {
			BeanUtils.copyProperties(bankTransactionsDTO, bankTransactions);
		}

		return bankTransactions;
	}

	// convert BankTransactions to (BankTransactionsDTO)
	public BankTransactionsDTO convertToBankTransactionsDTO(BankTransactions bankTransactions) {

		BankTransactionsDTO bankTransactionsDTO = new BankTransactionsDTO();
		if (bankTransactions != null) {
			BeanUtils.copyProperties(bankTransactions, bankTransactionsDTO);
		}

		return bankTransactionsDTO;
	}
}