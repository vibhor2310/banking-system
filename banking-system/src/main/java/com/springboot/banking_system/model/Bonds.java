package com.springboot.banking_system.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Bonds {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false)
	private String bond_type;
	
	@Column(nullable = false)
	private Double face_value;
	
	@Column(nullable = false)
	private LocalDate maturity_date;
	
	@Column(nullable = false)
	private double interest_rate;
	
	@OneToOne
	private Investment investment;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBond_type() {
		return bond_type;
	}

	public void setBond_type(String bond_type) {
		this.bond_type = bond_type;
	}

	public Double getFace_value() {
		return face_value;
	}

	public void setFace_value(Double face_value) {
		this.face_value = face_value;
	}

	public LocalDate getMaturity_date() {
		return maturity_date;
	}

	public void setMaturity_date(LocalDate maturity_date) {
		this.maturity_date = maturity_date;
	}

	public double getInterest_rate() {
		return interest_rate;
	}

	public void setInterest_rate(double interest_rate) {
		this.interest_rate = interest_rate;
	}

	public Investment getInvestment() {
		return investment;
	}

	public void setInvestment(Investment investment) {
		this.investment = investment;
	}
	
	
}
