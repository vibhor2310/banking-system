package com.springboot.banking_system.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.banking_system.model.Account;
import com.springboot.banking_system.model.Customer;
import com.springboot.banking_system.repository.AccountRepository;
import com.springboot.banking_system.repository.CustomerRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	public Account insert(Account account) {
		account.setAccountNumber(generateUniqueAccountNumber());
		Optional<Customer> optional = customerRepository.findById(account.getCustomer().getId());
		Customer customer = optional.get();
		 if (customer != null) {
	            customer.setAadharNumber(account.getAadharNumber());
	            customer.setPanNumber(account.getPanNumber());
	            customerRepository.save(customer);
	        }
		return accountRepository.save(account);
		
		
	}
	
	
	 private String generateUniqueAccountNumber() {
	        String accountNumber;
	        do {
//	             Generate a UUID as the account number
	            accountNumber = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
	        } while (accountRepository.findByAccountNumber(accountNumber) != null);

	        return accountNumber;
	    }

}
