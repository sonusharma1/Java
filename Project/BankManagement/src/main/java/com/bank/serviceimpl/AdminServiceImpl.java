package com.bank.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.entity.Admin;
import com.bank.entity.Customer;
import com.bank.exception.AccountNotFoundException;
import com.bank.repository.AccountDetailRepository;
import com.bank.repository.AdminRepository;
import com.bank.repository.CustomerRepository;
import com.bank.service.AdminService;
import com.bank.util.AdminConverter;
import com.bank.util.CustomerConverter;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private AdminConverter adminConverter;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerConverter customerConverter;

	@Autowired
	private AccountDetailRepository accountDetailRepository;

	@Override
	public String addNewAdmin(String existingUserName, String password, Admin newAdmin) {

		Admin existingAdmin = adminRepository.isUserExist(existingUserName);

		if (existingAdmin != null && existingAdmin.getUserName().equals(existingUserName)
				&& existingAdmin.getPassword().equals(password)) {

			if (newAdmin != null) {

				if (adminRepository.isUserExist(newAdmin.getUserName()) == null) {
					adminRepository.save(newAdmin);
					return "new admin created";
				}
				return "User name already exist.\nTry to create with another user name";
			}
			return "admin's user name and password can not be null!!!!";
		}
		return "existing admin's id/password are incorrect!!!!";
	}

	@Override
	public String updatePassword(String existingUserName, String oldPassword, String newPassword) {

		Admin existingAdmin = adminRepository.isUserExist(existingUserName);

		if (existingAdmin != null && existingAdmin.getUserName().equals(existingUserName)
				&& existingAdmin.getPassword().equals(oldPassword)) {

			existingAdmin.setPassword(newPassword);
			adminRepository.save(existingAdmin);

			return "password changed successfully";
		}

		return "user name / password are incorrect";
	}

	@Override
	public String deleteAdmin(String existingUserName, String password) {

		Admin existingAdmin = adminRepository.isUserExist(existingUserName);

		if (existingAdmin != null && existingAdmin.getUserName().equals(existingUserName)
				&& existingAdmin.getPassword().equals(password)) {

			adminRepository.delete(existingAdmin);
			return "admin/user deleted successfully";
		}

		return "user name / password are incorrect";
	}

	@Override
	public String deleteCustomerById(String existingUserName, String password, int customerId) {

		Admin existingAdmin = adminRepository.isUserExist(existingUserName);

		if (existingAdmin != null && existingAdmin.getUserName().equals(existingUserName)
				&& existingAdmin.getPassword().equals(password)) {

			Customer customer = customerRepository.findById(customerId)
					.orElseThrow(() -> new AccountNotFoundException("Customer", "Id", customerId));

			// If customer exists
			if (customer != null) {
				customerRepository.deleteById(customerId);
				accountDetailRepository.deleteById(customer.getAccountDetail().getAccountId());

				return "account deleted Successfully";
			}
			return "Account not deleted/account not found";
		}
		return "user name / password are incorrect";
	}

	@Override
	public String deleteAllCustomerData(String existingUserName, String password) {

		Admin existingAdmin = adminRepository.isUserExist(existingUserName);

		if (existingAdmin != null && existingAdmin.getUserName().equals(existingUserName)
				&& existingAdmin.getPassword().equals(password)) {

			customerRepository.deleteAll();
			accountDetailRepository.deleteAll();
			return "All Data deleted";
		}
		return "user name / password are incorrect";

	}

}
