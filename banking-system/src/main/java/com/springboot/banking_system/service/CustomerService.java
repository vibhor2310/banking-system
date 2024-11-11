package com.springboot.banking_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.banking_system.exception.ResourceNotFoundException;
import com.springboot.banking_system.model.Account;
import com.springboot.banking_system.model.Customer;
import com.springboot.banking_system.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	public Customer insert(Customer customer){
		
			return customerRepository.save(customer);
			
		}
		

	public Customer validate(int cid) throws ResourceNotFoundException {
		
		Optional<Customer>optional = customerRepository.findById(cid);
		
		if(optional.isEmpty()) {
			throw new ResourceNotFoundException("Given id is invalid try again...");
		}
		
		Customer customer = optional.get();
		
		return customer;
		
		
	}

	public void delete(int id) {
		customerRepository.deleteById(id);
		
	}

	public List<Customer> getCustomerDetail(int id) {
		return customerRepository.getCustomerDetail(id);
	}


}
