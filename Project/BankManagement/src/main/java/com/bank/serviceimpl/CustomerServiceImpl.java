package com.bank.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.emailsender.EmailDetails;
import com.bank.emailsender.EmailServiceImpl;
import com.bank.entity.AccountDetail;
import com.bank.entity.Customer;
import com.bank.exception.AccountNotFoundException;
import com.bank.model.CustomerDTO;
import com.bank.repository.AccountDetailRepository;
import com.bank.repository.CustomerRepository;
import com.bank.service.CustomerService;
import com.bank.util.CustomerConverter;

@Service
public class CustomerServiceImpl implements CustomerService {

	static Logger log = Logger.getLogger(CustomerServiceImpl.class.getName());

	// creating instance for Repository and Converter
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerConverter customerConverter;

	@Autowired
	private AccountDetailRepository accountDetailRepository;

	@Autowired
	private EmailServiceImpl emailServiceImpl;

	// Crud Operations
	// Creating Account
	@Override
	public String createAccount(Customer customer) {

		String lastAddedAccountId = accountDetailRepository.getLastAddedAccountId();

		// If Last Account Id is null
		if (lastAddedAccountId == null) {
			lastAddedAccountId = "0";
		}

		AccountDetail accountDetail = customer.getAccountDetail();
		accountDetail.setAccountNumber(1000000000 + 1 + Integer.parseInt(lastAddedAccountId));
		accountDetail.setOpenDate(LocalDate.now());

		customer.setAccountDetail(accountDetail);

		customerRepository.save(customer);

		log.info("Customer Account is Created at " + new java.util.Date());

		// send email to customer
		EmailDetails emailDetails = new EmailDetails();
		emailDetails.setRecipient(customer.getCustomerEmail());
		emailDetails.setSubject("Bank Account Created.");
		emailDetails.setMsgBody(customer.getCustomerName()
				+ ", Your bank account has been created successfully with our Karnataka Bank PVT. LTD.\nKindly contact us for any issue.\nThank You");
		emailServiceImpl.sendSimpleMail(emailDetails);

		return "Account Created Sucessfully :)";
	}

	// Updating Account
	@Override
	public CustomerDTO updateAccountDetail(int customerId, Customer customer) {

		Customer foundCustomer = customerRepository.findById(customerId)
				.orElseThrow(() -> new AccountNotFoundException("Customer", "Id", customerId));
		// If customer exists
		if (foundCustomer != null) {

			foundCustomer.setCustomerName(customer.getCustomerName());
			foundCustomer.setCustomerAddress(customer.getCustomerAddress());
			foundCustomer.setCustomerPanNo(customer.getCustomerPanNo());
			foundCustomer.setCustomerAdharNo(customer.getCustomerAdharNo());
			foundCustomer.setCustomerPhone(customer.getCustomerPhone());
			foundCustomer.setCustomerEmail(customer.getCustomerEmail());

			customerRepository.save(foundCustomer);
			log.info("Customer Account is Updated at " + new java.util.Date());

			return customerConverter.convertToCustomerDTO(foundCustomer);
		}

		return null;
	}

	// geting customer by ID
	@Override
	public CustomerDTO getAccountDetailByCustomerId(int customerId) {

		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new AccountNotFoundException("Customer", "Id", customerId));

		log.info("Customer Searched at " + new java.util.Date());
		return customerConverter.convertToCustomerDTO(customer);
	}

	// Get All AccountDetails
	@Override
	public List<CustomerDTO> getAllAccountDetails() {

		List<Customer> customers = customerRepository.findAll();
		List<CustomerDTO> customerDTOs = new ArrayList<>();

		// for Each Loop
		for (Customer customer : customers) {
			customerDTOs.add(customerConverter.convertToCustomerDTO(customer));
		}

		return customerDTOs;
	}

	// CheckBalance using Customer Id
	@Override
	public String checkBalanceById(int customerId) {

		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new AccountNotFoundException("Customer", "Id", customerId));

		// If Customer exists
		if (customer != null) {
			customer.getAccountDetail().getAccountBalance();
			return customer.getCustomerName() + " Jee, Your Account Balance is "
					+ customer.getAccountDetail().getAccountBalance();
		}
		log.error("Customer Account not found at " + new java.util.Date());
		return "account not found";

	}

	// Get Customer using Name
	@Override
	public List<CustomerDTO> getCustomerByName(String customerName) {

		List<Customer> customer = customerRepository.getCustomerByName(customerName);
		List<CustomerDTO> customerDTO = new ArrayList<>();

		// ForEach loop
		for (Customer c : customer) {
			customerDTO.add(customerConverter.convertToCustomerDTO(c));
		}
		return customerDTO;

	}

}
