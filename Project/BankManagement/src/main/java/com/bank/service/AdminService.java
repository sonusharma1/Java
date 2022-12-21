package com.bank.service;

import com.bank.entity.Admin;

public interface AdminService {

	String addNewAdmin(String existingUserName, String existingPassword, Admin newAdmin);

	String updatePassword(String existingUserName, String oldPassword, String newPassword);

	String deleteAdmin(String existingUserName, String password);

	String deleteCustomerById(String existingUserName, String password, int customerId);

	String deleteAllCustomerData(String existingUserName, String password);
}
