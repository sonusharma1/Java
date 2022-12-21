package com.bank.serviceimpl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.entity.AccountDetail;
import com.bank.entity.BankTransactions;
import com.bank.exception.MaxDepositException;
import com.bank.exception.MaxWithdrawException;
import com.bank.model.AccountDetailDTO;
import com.bank.repository.AccountDetailRepository;
import com.bank.repository.BankTransactionRepository;
import com.bank.repository.CustomerRepository;
import com.bank.service.BankTransactionService;
import com.bank.util.AccountDetailConverter;
import com.bank.util.BankTransactionsConverter;

@Service
public class BankTransactionImpl implements BankTransactionService {

	static Logger log = Logger.getLogger(BankTransactionImpl.class.getName());

	// creating instance for Repository and Converter
	@Autowired
	private BankTransactionRepository bankTransactionRepository;

	@Autowired
	private BankTransactionsConverter bankTransactionsConverter;

	@Autowired
	private AccountDetailConverter accountDetailConverter;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AccountDetailRepository accountDetailRepository;

	// Custom method for depositing amount into account
	@Override
	public String depositAmount(int accountId, String description, double depositAmount) {

		if (depositAmount > 50000 && description.equalsIgnoreCase("bycash")) {
			throw new MaxDepositException("BankTransactions", "Deposit");
		}

		// finding account by accountID
		AccountDetail accountById = accountDetailRepository.findById(accountId).get();

		// If account exists
		if (accountById != null) {
			BankTransactions bankTransactions = new BankTransactions();

			// setting transaction fields

			bankTransactions.setDateOfTransaction(LocalDate.now());
			bankTransactions.setTransactionType("Credited");
			bankTransactions.setTransactionAmount(depositAmount);
			bankTransactions.setDescriptionOfTransaction(description);

			bankTransactions.setAccountDetail(accountById);
			bankTransactionRepository.save(bankTransactions);

			accountById.setAccountBalance(accountById.getAccountBalance() + depositAmount);
			List<BankTransactions> bankTransactonList = accountById.getBankTransactions();

			bankTransactonList.add(bankTransactions);

			accountById.setBankTransactions(bankTransactonList);

			accountDetailRepository.save(accountById);

			log.info("Customer Deposited at " + new java.util.Date());
			// if the condition is true then return the below statement with amount
			return depositAmount + " Credited  to your Account and balance is  " + (accountById.getAccountBalance());

		}
		// else return
		log.error("Customer Account not found at " + new java.util.Date());
		return "Try again Later";
	}

	// Custom method to withdraw amount from accountID
	@Override
	public String withdrawAmount(int accountId, String description, double withdrawAmount) {

		if (withdrawAmount > 25000 && description.equalsIgnoreCase("bycash")) {
			throw new MaxWithdrawException("BankTransactions", "Withdrawl");
		}

		// finding account by accountID
		AccountDetail accountById = accountDetailRepository.findById(accountId).get();

		// If account exists
		if (accountById != null) {

			// if account balance is less than withdrawl amount
			if (accountById.getAccountBalance() < withdrawAmount) {
				log.error("withdraw amount is greater than account balance " + new java.util.Date());
				return "Insufficient Balance!!!!\nYour Balance is Rs." + accountById.getAccountBalance();
			}

			BankTransactions bankTransactions = new BankTransactions();

			bankTransactions.setDateOfTransaction(LocalDate.now());
			bankTransactions.setTransactionType("Debited");
			bankTransactions.setTransactionAmount(withdrawAmount);
			bankTransactions.setDescriptionOfTransaction(description);

			bankTransactions.setAccountDetail(accountById);
			bankTransactionRepository.save(bankTransactions);

			accountById.setAccountBalance(accountById.getAccountBalance() - withdrawAmount);
			List<BankTransactions> bankTransactonList = accountById.getBankTransactions();

			bankTransactonList.add(bankTransactions);

			accountById.setBankTransactions(bankTransactonList);

			accountDetailRepository.save(accountById);

			log.info("Customer withdraw at " + new java.util.Date());
			// if the condition is true then return the below statement with amount
			return withdrawAmount + " Debited from your Account and balance is  " + (accountById.getAccountBalance());

		}
		// else return
		return "Try again Later ";

	}

	// Custom method to print the transaction/Transactions History od the Account
	@Override
	public AccountDetailDTO transactionHistory(int accountId) {

		// finding account by accountID
		AccountDetail accountDetail = accountDetailRepository.findById(accountId).get();

		Collections.sort(accountDetail.getBankTransactions(), (o1, o2) -> {
			Integer i1 = (Integer) o1.getTransactionId();
			Integer i2 = (Integer) o2.getTransactionId();
			return i2.compareTo(i1);
		});

		log.info("Customer Viewed Transaction History at " + new java.util.Date());
		return accountDetailConverter.convertToAccountDetailDTO(accountDetail);
	}

	// Custom Method to transfer amount from one account to another account by ID
	@Override
	public String fundTransfer(int senderAccountId, int receiverAccountId, double amount, String description) {

		// finding account by accountID
		AccountDetail senderDetail = accountDetailRepository.findById(senderAccountId).get();
		AccountDetail reciverDetail = accountDetailRepository.findById(receiverAccountId).get();

		// If account exists
		if (senderDetail != null && reciverDetail != null) {

			// if account balance is less than withdrawl amount
			if (senderDetail.getAccountBalance() < amount) {
				log.error("Fund Transfer amount is greater than account balance " + new java.util.Date());
				return "Insufficient Balance!!!!\nYour Balance is Rs." + senderDetail.getAccountBalance();
			}

			// creating transaction for sender --------------------------------------------
			BankTransactions senderTransactions = new BankTransactions();

			senderTransactions.setDateOfTransaction(LocalDate.now());
			senderTransactions.setTransactionType("Debited");
			senderTransactions.setTransactionAmount(amount);
			senderTransactions.setDescriptionOfTransaction(description);

			senderTransactions.setAccountDetail(senderDetail);
			bankTransactionRepository.save(senderTransactions);

			senderDetail.setAccountBalance(senderDetail.getAccountBalance() - amount);
			List<BankTransactions> senderTransactonList = senderDetail.getBankTransactions();

			senderTransactonList.add(senderTransactions);

			senderDetail.setBankTransactions(senderTransactonList);

			accountDetailRepository.save(senderDetail);

			// creating transaction for sender --------------------------------------------

			BankTransactions receiverTransactions = new BankTransactions();

			receiverTransactions.setDateOfTransaction(LocalDate.now());
			receiverTransactions.setTransactionType("Credited");
			receiverTransactions.setTransactionAmount(amount);
			receiverTransactions.setDescriptionOfTransaction("NEFT:" + senderDetail.getAccountNumber());

			receiverTransactions.setAccountDetail(reciverDetail);
			bankTransactionRepository.save(receiverTransactions);

			reciverDetail.setAccountBalance(reciverDetail.getAccountBalance() + amount);
			List<BankTransactions> bankTransactonList = reciverDetail.getBankTransactions();

			bankTransactonList.add(receiverTransactions);

			reciverDetail.setBankTransactions(bankTransactonList);

			accountDetailRepository.save(reciverDetail);

			// Method for Displaying the Transfer
			// details ------------------------------
			String acNoOfSender = String.valueOf(senderDetail.getAccountNumber());
			String maskAcNoOfSender = acNoOfSender.replaceAll("\\w(?=\\w{4})", "X");

			String acNoOfReceiver = String.valueOf(reciverDetail.getAccountNumber());
			String maskAcNoOfReceiver = acNoOfReceiver.replaceAll("\\w(?=\\w{4})", "X");

			log.info("fund transfered successfully at " + new java.util.Date());
			// if the condition is true then return the below statement with amount
			return "Your a/c no. " + maskAcNoOfSender + "\nis debited for Rs." + amount + " on\n" + LocalDate.now()
					+ " and Credited to a/c\nno. " + maskAcNoOfReceiver + "\n-Laxmi ChitFund";
		}
		// else return
		return " ****Kindly Try again , Amount not deducted**** ";
	}

}
