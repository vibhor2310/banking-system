package com.springboot.banking_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.banking_system.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

	Account findByAccountNumber(String accountNumber);

	@Query("select a from Account a join a.customer c where c.id = ?1 ")
	List<Account> getAccountDetails(int cid);

}
