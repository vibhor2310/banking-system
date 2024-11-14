package com.springboot.banking_system.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.banking_system.exception.ResourceNotFoundException;
import com.springboot.banking_system.model.Admin;
import com.springboot.banking_system.model.Investment;
import com.springboot.banking_system.repository.InvestmentRepository;

@Service
public class InvestmentService {

	@Autowired
	private InvestmentRepository investmentRepository;
	
	public Investment insert(Investment investment) {
		return investmentRepository.save(investment);
	}

	
//		// In InvestmentService class
//		public void verify(Investment investment) throws ResourceNotFoundException {
//		    // Check if the investment is null
//		    if (investment == null) {
//		        throw new ResourceNotFoundException("Investment not found.");
//		    }
//		    
//		    // Check if the investment status is valid
//		    if (investment.getStatus() == null || investment.getStatus().isEmpty()) {
//		        throw new ResourceNotFoundException("Investment status is missing.");
//		    }
//
//		    // Check if the investment type is valid
//		    if (investment.getType() == null) {
//		        throw new ResourceNotFoundException("Investment type is not specified.");
//		    }
//
//		    // Check if purchase date is provided
//		    if (investment.getPurchase_date() == null) {
//		        throw new ResourceNotFoundException("Purchase date is missing for the investment.");
//		    }
//
//		    // Check if account is associated with the investment
//		    if (investment.getAccount() == null) {
//		        throw new ResourceNotFoundException("No account associated with the investment.");
//		    }
//
//		    // Additional checks can be added as needed
//		}
//
//	
}
