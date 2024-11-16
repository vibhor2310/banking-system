package com.springboot.banking_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.banking_system.model.Loan;
import com.springboot.banking_system.repository.LoanRepository;

@Service
public class LoanService {
	
	@Autowired
	private LoanRepository loanRepository;

	public Loan insert(Loan loan) {
		// TODO Auto-generated method stub
		return loanRepository.save(loan);
	}

	public List<Loan> getLoanDetails(int aid) {
		// TODO Auto-generated method stub
		return loanRepository.getLoanDetails(aid);
	}

	

}
