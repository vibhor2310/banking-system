package com.springboot.banking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.banking_system.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

	Account findByAccountNumber(String accountNumber);

}
