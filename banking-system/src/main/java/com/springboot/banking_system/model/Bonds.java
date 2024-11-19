package com.springboot.banking_system.model;

import java.time.LocalDate;

import com.springboot.banking_system.enums.BondType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Bonds {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	private double faceValue;
	
	@Column
	private double interestRate;
	
	@Enumerated(EnumType.STRING)
	private BondType bondType;
	
	private LocalDate maturityDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(double faceValue) {
		this.faceValue = faceValue;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public BondType getBondType() {
		return bondType;
	}

	public void setBondType(BondType bondType) {
		this.bondType = bondType;
	}

	public LocalDate getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(LocalDate maturityDate) {
		this.maturityDate = maturityDate;
	}
}
