package com.springboot.banking_system.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class FixedDeposit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false)
	private double deposit_amount;
	
	@Column(nullable = false)
	private double interest_rate;
	
	@Column(nullable = false)
	private LocalDate maturity_date;
	
	@OneToOne
	private Investment investment;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getDeposit_amount() {
		return deposit_amount;
	}

	public void setDeposit_amount(double deposit_amount) {
		this.deposit_amount = deposit_amount;
	}

	public double getInterest_rate() {
		return interest_rate;
	}

	public void setInterest_rate(double interest_rate) {
		this.interest_rate = interest_rate;
	}

	public LocalDate getMaturity_date() {
		return maturity_date;
	}

	public void setMaturity_date(LocalDate maturity_date) {
		this.maturity_date = maturity_date;
	}

	public Investment getInvestment() {
		return investment;
	}

	public void setInvestment(Investment investment) {
		this.investment = investment;
	}
	
}
