package com.springboot.banking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.banking_system.enums.InvestmentStatus;
import com.springboot.banking_system.model.Investment;



@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Integer>{

	
//	@Query("Update Investment i set i.investmentStatus =?2where i.id=?1 ")
//	//void updateInvestmentStatus(InvestmentStatus investmentStatus,int id);
//	void updateInvestmentStatus(int id,InvestmentStatus investmentStatus);

}
