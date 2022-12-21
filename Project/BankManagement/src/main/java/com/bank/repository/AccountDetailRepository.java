package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bank.entity.AccountDetail;

public interface AccountDetailRepository extends JpaRepository<AccountDetail, Integer> {

	// SQl query to get last accountID.
	@Query("Select max(accountId) from AccountDetail")
	String getLastAddedAccountId();

	// for admin use pending
	// @Query("Select from AccountDetail where accountBalance>=:s")
	// List<AccountDetail>
}
