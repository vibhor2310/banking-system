package com.springboot.banking_system.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Card {
	@ManyToOne
	private Account account;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(unique = true, nullable = false)
	private String cardNumber;
	
	@Column(nullable = false)
	private String status = "Approved";
	
	@Column(nullable = false)
    private LocalDate expiryDate;
	
	@Column(nullable = false)
	private int cvv;
	
	@Column(nullable = false)
	private double balance;
	
	
}