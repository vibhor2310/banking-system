package com.springboot.banking_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.banking_system.exception.ResourceNotFoundException;
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
	 

	public List<Account> getAccountDetails(int cid) {
		return accountRepository.getAccountDetails(cid);
	}


	public Account validateIdAndAmount(int aid,double amount) throws ResourceNotFoundException {
		
		Optional<Account>optional = accountRepository.findById(aid);

		if(optional.isEmpty()) {
			throw new ResourceNotFoundException("Given id is invalid try again...");
		}
		if(amount<=0)
			throw new ResourceNotFoundException("Amount cannot be negative or zero");
		
		Account account = optional.get();
		
		return account;
		
	}
	
public Account validateIdAndAmountAndBalance(int aid,double amount,String reaccno) throws ResourceNotFoundException {
		
		Optional<Account>optional = accountRepository.findById(aid);
		
		List<Account>list = accountRepository.findByAccountNumber(reaccno);

		if(optional.isEmpty()||list.isEmpty()) {
			throw new ResourceNotFoundException("Given id is invalid try again...");
		}
		if(amount<=0)
			throw new ResourceNotFoundException("Amount cannot be negative or zero");
		
		
		Account account = optional.get();
		
		double balance = account.getBalance();
		
		if(balance<amount)
			throw new ResourceNotFoundException("Insufficient Balance...");
		
		return account;
		
	}


}
