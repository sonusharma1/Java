package com.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.entity.Customer;
import com.bank.model.CustomerDTO;
import com.bank.service.CustomerService;
import com.bank.util.CustomerConverter;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerConverter customerConverter;

	@PostMapping("/createAccount")
	public String createAccount(@RequestBody CustomerDTO customerDTO) {

		Customer customer = customerConverter.convertToEntity(customerDTO);
		return customerService.createAccount(customer);
	}

	@PutMapping("/updateAccount/{id}")
	public CustomerDTO updateAccountDetail(@PathVariable("id") int id, @RequestBody CustomerDTO customerDTO) {
		Customer customer = customerConverter.convertToEntity(customerDTO);
		return customerService.updateAccountDetail(id, customer);
	}

	@GetMapping("getAccountDetailByCustomerId/{id}")
	public CustomerDTO getAccountDetailByCustomerId(@PathVariable int id) {
		return customerService.getAccountDetailByCustomerId(id);
	}

	@GetMapping("/getAllAccountDetails")
	public List<CustomerDTO> getAllAccountDetails() {
		return customerService.getAllAccountDetails();
	}

	@GetMapping("/checkBalance/{id}")
	public String checkBalanceById(@PathVariable int id) {
		return customerService.checkBalanceById(id);
	}

	@GetMapping("/customerByName/{customerName}")
	public List<CustomerDTO> getCustomerByName(@PathVariable("customerName") String customerName) {

		return customerService.getCustomerByName(customerName);
	}
}
