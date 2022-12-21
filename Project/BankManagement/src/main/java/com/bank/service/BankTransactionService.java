package com.bank.service;

import com.bank.model.AccountDetailDTO;

public interface BankTransactionService {

	// defining custom methods to perform bank related operations

	String depositAmount(int accountId, String description, double depositAmount);

	String withdrawAmount(int accountId, String description, double withdrawAmount);

	AccountDetailDTO transactionHistory(int accountId);

	String fundTransfer(int senderAccountId, int receiverAccountId, double amount, String description);

}
