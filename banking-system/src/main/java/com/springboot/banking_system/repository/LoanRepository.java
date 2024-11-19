package com.springboot.banking_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.banking_system.model.Loan;

import jakarta.transaction.Transactional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer>{

	@Query("select l from Loan l join l.account a where a.id = ?1 ")
	List<Loan> getLoanDetails(int aid);

	@Modifying
    @Transactional
	@Query("update Loan l set l.interestRate = ?1")
	void updateNewRate(double newInterestRate);

}
