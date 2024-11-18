package com.springboot.banking_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.banking_system.model.Card;

public interface  CardRepository extends JpaRepository<Card, Integer>{

	Card findByCardNumber(String cardNumber);

	Card findByCvv(String cvv);

	@Query("select c from Card c join c.account a where a.id = ?1")
	List<Card> getCardDetails(int aid);

}
