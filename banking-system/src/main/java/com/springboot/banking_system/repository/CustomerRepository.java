package com.springboot.banking_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.banking_system.model.Account;
import com.springboot.banking_system.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	@Query("select c from Customer c where c.id = ?1")
	List<Customer> getCustomerDetail(int id);



}
