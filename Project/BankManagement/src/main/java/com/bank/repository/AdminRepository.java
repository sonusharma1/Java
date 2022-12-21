package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bank.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

	@Query("from Admin where userName = :s")
	Admin isUserExist(@Param("s") String username);
}
