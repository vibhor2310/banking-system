package com.springboot.banking_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.banking_system.enums.InvestmentStatus;
import com.springboot.banking_system.exception.ResourceNotFoundException;
import com.springboot.banking_system.model.Admin;
import com.springboot.banking_system.model.Investment;
import com.springboot.banking_system.repository.InvestmentRepository;

@Service
public class InvestmentService {
	
	@Autowired
	private InvestmentRepository investmentRepository;

	public List<Investment> showAllInvestments() {
		
		return investmentRepository.findAll();
	}

//	public void updateInvestmentStatus( int id,InvestmentStatus investmentStatus) {
//		investmentRepository.updateInvestmentStatus(id,investmentStatus);
//		
//	}

	public Investment validate(int id) throws ResourceNotFoundException{
		Optional<Investment> optional = investmentRepository.findById(id);
		if(optional.isEmpty())
			throw new ResourceNotFoundException("Investment Not Found");
		
		Investment investment = optional.get();
		return investment;
		
	}


	

}
