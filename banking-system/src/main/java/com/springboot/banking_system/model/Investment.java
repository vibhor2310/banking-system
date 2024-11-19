package com.springboot.banking_system.model;

import java.time.LocalDate;

import com.springboot.banking_system.enums.InvestmentStatus;
import com.springboot.banking_system.enums.InvestmentType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Investment {
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private int id;
	
	@Enumerated(EnumType.STRING)
	private InvestmentType investmentType;
	
	@Column
	private LocalDate purchaseDate;
	
	@Enumerated(EnumType.STRING)
	private InvestmentStatus investmentStatus;
	
	@ManyToOne
	private Account account;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public InvestmentType getInvestmentType() {
		return investmentType;
	}

	public void setInvestmentType(InvestmentType investmentType) {
		this.investmentType = investmentType;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public InvestmentStatus getInvestmentStatus() {
		return investmentStatus;
	}

	public void setInvestmentStatus(InvestmentStatus investmentStatus) {
		this.investmentStatus = investmentStatus;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
