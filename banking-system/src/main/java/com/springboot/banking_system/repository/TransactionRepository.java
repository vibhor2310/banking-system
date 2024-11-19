package com.springboot.banking_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.banking_system.enums.TransactionType;
import com.springboot.banking_system.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
	
	@Query("select t from Transaction t join t.account a where a.id=?1")
	List<Transaction> getTransactionHistory(int aid);
	
	@Query("select t from Transaction t where t.TransactionType = ?1")
	List<Transaction>findByTransactionType(TransactionType TransactionType);

}
