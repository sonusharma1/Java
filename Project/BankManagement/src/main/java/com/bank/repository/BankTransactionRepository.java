package com.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bank.entity.BankTransactions;

public interface BankTransactionRepository extends JpaRepository<BankTransactions, Integer> {

	// SQl query to get All Transactions By AccountId.
	@Query("from BankTransactions where accountDetail=:id")
	List<BankTransactions> getAllTransactionsByAccountId(@Param("id") int accountId);

////	 where AccountDetail.accountId = id and dateOfTransaction BETWEEN :fDate and :toDate 
//	@Query("from BankTransactions where accountDetail=:id and dateOfTransaction>=:fDate and dateOfTransaction<=:toDate")
//	List<BankTransactions> getTransactionsBetweenDateRange(@Param("id") int accountId, @Param("fDate") String fromDate,
//			@Param("toDate") String toDate);
}
