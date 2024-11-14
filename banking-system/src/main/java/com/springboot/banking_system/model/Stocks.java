package com.springboot.banking_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Stocks {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable =false)
	private String stock_ticker;
	
	@Column(nullable =false)
	private int number_of_shares;
	
	@Column(nullable =false)
	private Double purchase_price;
	
	@OneToOne
	private Investment investment;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStock_ticker() {
		return stock_ticker;
	}

	public void setStock_ticker(String stock_ticker) {
		this.stock_ticker = stock_ticker;
	}

	public int getNumber_of_shares() {
		return number_of_shares;
	}

	public void setNumber_of_shares(int number_of_shares) {
		this.number_of_shares = number_of_shares;
	}

	public Double getPurchase_price() {
		return purchase_price;
	}

	public void setPurchase_price(Double purchase_price) {
		this.purchase_price = purchase_price;
	}

	public Investment getInvestment() {
		return investment;
	}

	public void setInvestment(Investment investment) {
		this.investment = investment;
	}
	
	
	
}
