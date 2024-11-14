package com.springboot.banking_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class MutualFunds {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false)
	private String fund_name;
	
	@Column(nullable = false)
	private int units_purchased;
	
	@Column(nullable = false)
	private double purchase_price;
	
	@OneToOne
	private Investment investment;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFund_name() {
		return fund_name;
	}

	public void setFund_name(String fund_name) {
		this.fund_name = fund_name;
	}

	public int getUnits_purchased() {
		return units_purchased;
	}

	public void setUnits_purchased(int units_purchased) {
		this.units_purchased = units_purchased;
	}

	public double getPurchase_price() {
		return purchase_price;
	}

	public void setPurchase_price(double purchase_price) {
		this.purchase_price = purchase_price;
	}

	public Investment getInvestment() {
		return investment;
	}

	public void setInvestment(Investment investment) {
		this.investment = investment;
	}
	
}
