package com.springboot.banking_system.dto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.springboot.banking_system.enums.AccountType;
import com.springboot.banking_system.enums.TransactionType;

@Component
public class AccountStatementDto {
	private String accountNumber;
	private AccountType accountType;
	private double balance;
	private TransactionType TransactionType;
	private LocalDate TransactionDate;
	private double amount;
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public TransactionType getTransactionType() {
		return TransactionType;
	}
	public void setTransactionType(TransactionType transactionType) {
		TransactionType = transactionType;
	}
	public LocalDate getTransactionDate() {
		return TransactionDate;
	}
	public void setTransactionDate(LocalDate transactionDate) {
		TransactionDate = transactionDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
