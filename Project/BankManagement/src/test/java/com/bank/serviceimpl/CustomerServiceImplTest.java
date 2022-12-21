package com.bank.serviceimpl;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bank.entity.AccountDetail;
import com.bank.entity.Customer;
import com.bank.repository.CustomerRepository;
import com.bank.service.CustomerService;
import com.bank.util.CustomerConverter;

@SpringBootTest
class CustomerServiceImplTest {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerConverter customerConverter;

	@MockBean
	private CustomerRepository customerRepository;

	private Customer customer;

	private AccountDetail accountDetail;

	@BeforeEach
	void init() {

		accountDetail = accountDetail.builder().accountNumber(1000000001).openDate(LocalDate.now()).accountType("SB")
				.accountBalance(500).build();
		customer = customer.builder().customerName("Sonu").customerAddress("Patna").customerPanNo("SH0458")
				.customerAdharNo(78456521).customerPhone(784598654).customerEmail("sonu@gmail.com")
				.accountDetail(accountDetail).build();
	}

	@Test
	void testCreateCustomer() {

		Mockito.when(customerRepository.save(customer)).thenReturn(customer);
//		assertEquals(customerService.createAccount(customer), "Account Created Sucessfully :)");
		assertThat(customerService.createAccount(customer)).isEqualTo("Account Created Sucessfully :)");
	}

//	@Test
//	void testUpdateAccountDetail() {
//
//		Customer updatedCustomer = customer;
//		updatedCustomer.setCustomerName("Sujal");
//
//		Mockito.when(customerRepository.findById(0).get()).thenReturn(customer);
//		Mockito.when(customerRepository.save(customer)).thenReturn(updatedCustomer);
//
//		assertThat(customerService.updateAccountDetail(0, updatedCustomer).getCustomerName())
//				.isEqualTo(updatedCustomer.getCustomerName());
//	}

//	@Test
//	void testGetAccountDetailByCustomerId() {
//
////		Mockito.when(customerRepository.save(customer)).thenReturn(customer);
////		System.out.println(customerRepository.findById(0).get().toString());
////		Mockito.when(customerRepository.findById(0).get()).thenReturn(customer);
////		assertThat(customerService.getAccountDetailByCustomerId(0).getCustomerName()).isEqualTo("Sonu");
//	}

}
