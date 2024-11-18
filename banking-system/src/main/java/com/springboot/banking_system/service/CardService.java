package com.springboot.banking_system.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.banking_system.model.Card;
import com.springboot.banking_system.repository.CardRepository;

@Service
public class CardService {
	
	@Autowired
	private CardRepository cardRepository;

	public Card insert(Card card) {
		card.setCardNumber(generateUniqueCardNumber());
		card.setCvv(generateUniqueCVV());
		return cardRepository.save(card);
		
	}
	
	 private String generateUniqueCardNumber() {
	        String cardNumber;
	        Random random = new Random();
	        do {
	        	// Generate a random 16-digit number
	            cardNumber = String.format("%016d", random.nextLong() & Long.MAX_VALUE);
	        } while (cardRepository.findByCardNumber(cardNumber) != null);

	        return cardNumber;
	    }
	 
	 private String generateUniqueCVV() {
		    String cvv;
		    Random random = new Random();

		    do {
		        // Generate a random 3-digit number
		        cvv = String.format("%03d", random.nextInt(1000)); // generates a number between 0 and 999
		    } while (cardRepository.findByCvv(cvv) != null); // Check for uniqueness

		    return cvv;
		}

	public List<Card> getCardDetails(int aid) {
		return cardRepository.getCardDetails(aid);
	}

}
