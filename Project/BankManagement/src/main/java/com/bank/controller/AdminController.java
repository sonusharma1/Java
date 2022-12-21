package com.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.entity.Admin;
import com.bank.model.AdminDTO;
import com.bank.service.AdminService;
import com.bank.util.AdminConverter;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private AdminConverter adminConverter;

	@PostMapping("/addNewAdmin/{userName}/{password}")
	public String addNewAdmin(@PathVariable("userName") String existingUserName,
			@PathVariable("password") String existingAdminPassword, @RequestBody AdminDTO newAdminDTO) {
		Admin admin = adminConverter.convertToEntity(newAdminDTO);
		return adminService.addNewAdmin(existingUserName, existingAdminPassword, admin);
	}

	@PutMapping("/updatePassword/{userName}/{oldPassword}/{newPassword}")
	public String updatePassword(@PathVariable("userName") String existingUserName,
			@PathVariable("oldPassword") String oldPassword, @PathVariable("newPassword") String newPassword) {
		return adminService.updatePassword(existingUserName, oldPassword, newPassword);
	}

	@DeleteMapping("/deleteAdmin/{userName}/{password}")
	public String deleteAdmin(@PathVariable("userName") String existingUserName,
			@PathVariable("password") String password) {
		return adminService.deleteAdmin(existingUserName, password);
	}

	@DeleteMapping("/admin/deleteCustomer/{userName}/{password}/{customerId}")
	public String deleteCustomerById(@PathVariable("userName") String existingUserName,
			@PathVariable("password") String password, @PathVariable("customerId") int customerId) {
		return adminService.deleteCustomerById(existingUserName, password, customerId);
	}

	@DeleteMapping("/admin/deleteAllCustomer/{userName}/{password}")
	public String deleteAllCustomerData(@PathVariable("userName") String existingUserName,
			@PathVariable("password") String password) {
		return adminService.deleteAllCustomerData(existingUserName, password);
	}

}
