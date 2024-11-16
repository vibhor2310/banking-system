package com.springboot.banking_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.banking_system.enums.TransactionType;
import com.springboot.banking_system.model.Account;
import com.springboot.banking_system.model.Transaction;
import com.springboot.banking_system.repository.AccountRepository;
import com.springboot.banking_system.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AccountRepository accountRepository;

	public Transaction depositMoney(int aid, double amount) {
		
		Account account = accountRepository.findById(aid).get();
		accountRepository.depositMoney(aid, amount);
		
		Transaction transaction = new Transaction();
		transaction.setAccountNumber(account.getAccountNumber());
		transaction.setTransactionType(TransactionType.DEPOSIT);
		transaction.setAmount(amount);
		transaction.setAccount(account);
		
		return transactionRepository.save(transaction);
		
		
		
		
	}

	public Transaction withdrawMoney(int aid, double amount) {

		Account account = accountRepository.findById(aid).get();
		accountRepository.withdrawMoney(aid, amount);
		
		Transaction transaction = new Transaction();
		transaction.setAccountNumber(account.getAccountNumber());
		transaction.setTransactionType(TransactionType.WITHDRAW);
		transaction.setAmount(amount);
		transaction.setAccount(account);
		
		return transactionRepository.save(transaction);
		
	}

	public Transaction transferMoney(int aid, String reaccno, double amount) {
		Account account = accountRepository.findById(aid).get();
		String senderAccount = account.getAccountNumber();
		String receiverAccount = reaccno;
		
		accountRepository.withdraw(senderAccount, amount);
		accountRepository.deposit(receiverAccount, amount);
		
		Transaction transaction = new Transaction();
		transaction.setAccountNumber(senderAccount);
		transaction.setTransactionType(TransactionType.TRANSFER);
		transaction.setAmount(amount);
		transaction.setAccount(account);
		
		return transactionRepository.save(transaction);
	}
	
	

}
