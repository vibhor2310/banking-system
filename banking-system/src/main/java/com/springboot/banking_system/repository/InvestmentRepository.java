package com.springboot.banking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.banking_system.model.Admin;
import com.springboot.banking_system.model.Investment;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Integer> {

	
}
