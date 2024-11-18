package com.springboot.banking_system.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.banking_system.dto.AccountStatementDto;
import com.springboot.banking_system.enums.AccountType;
import com.springboot.banking_system.enums.TransactionType;
import com.springboot.banking_system.exception.ResourceNotFoundException;
import com.springboot.banking_system.model.Account;
import com.springboot.banking_system.model.Customer;
import com.springboot.banking_system.model.Transaction;
import com.springboot.banking_system.repository.AccountRepository;
import com.springboot.banking_system.repository.CustomerRepository;
import com.springboot.banking_system.repository.TransactionRepository;



@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;

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
		
		if(account.getStatus().equalsIgnoreCase("Not Approved"))
			throw new ResourceNotFoundException("Account is not approved yet...");
		
		return account;
		
	}
	
public Account validateIdAndAmountAndBalance(int aid,double amount,String reaccno) throws ResourceNotFoundException {
		
		Optional<Account>optional = accountRepository.findById(aid);
		
		Account acc= accountRepository.findByAccountNumber(reaccno);

		if(optional.isEmpty()||acc==null) {
			throw new ResourceNotFoundException("Given id is invalid try again...");
		}
		if(amount<=0)
			throw new ResourceNotFoundException("Amount cannot be negative or zero");
		
		
		Account account = optional.get();
		
		double balance = account.getBalance();
		
		if(balance<amount)
			throw new ResourceNotFoundException("Insufficient Balance...");
		
		if(account.getStatus().equalsIgnoreCase("Not Approved"))
			throw new ResourceNotFoundException("Account is not approved yet...");
		
		return account;
		
	}


   public Account validate(int aid) throws ResourceNotFoundException {
	Optional<Account>optional = accountRepository.findById(aid);

	if(optional.isEmpty()) {
		throw new ResourceNotFoundException("Given id is invalid try again...");
	}
	
	Account account = optional.get();
	
	if(account.getStatus().equalsIgnoreCase("Not Approved"));
		
	
	return account;
}


   public Account insertUpdatedData(Account account) {
	return accountRepository.save(account);
}


   public List<Transaction> getTransactionHistory(int aid) {
	return transactionRepository.getTransactionHistory(aid);
  }



}
