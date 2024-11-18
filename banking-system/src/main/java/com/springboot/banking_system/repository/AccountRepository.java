package com.springboot.banking_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.banking_system.model.Account;

import jakarta.transaction.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{


	@Query("select a from Account a join a.customer c where c.id = ?1 ")
	List<Account> getAccountDetails(int cid);

	@Modifying
    @Transactional
    @Query("update Account a SET a.balance = a.balance+?2 where a.id = ?1")
	void depositMoney(int aid, double amount);


	@Modifying
    @Transactional
    @Query("update Account a SET a.balance = a.balance-?2 where a.id = ?1")
	void withdrawMoney(int aid, double amount);

	Account findByAccountNumber(String accountNumber);

	@Modifying
    @Transactional
    @Query("UPDATE Account a SET a.balance = a.balance - :amount WHERE a.accountNumber = :senderAccount")
	void withdraw(String senderAccount, double amount);

	@Modifying
    @Transactional
     @Query("UPDATE Account a SET a.balance = a.balance + :amount WHERE a.accountNumber = :receiverAccount")
	void deposit(String receiverAccount, double amount);





}
