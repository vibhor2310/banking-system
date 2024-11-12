package com.springboot.banking_system.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.springboot.banking_system.dto.ResponseMessageDto;
import com.springboot.banking_system.exception.ResourceNotFoundException;
import com.springboot.banking_system.model.Account;
import com.springboot.banking_system.model.Customer;
import com.springboot.banking_system.model.Employee;
import com.springboot.banking_system.model.User;
import com.springboot.banking_system.service.AccountService;
import com.springboot.banking_system.service.CustomerService;
import com.springboot.banking_system.service.EmployeeService;
import com.springboot.banking_system.service.UserService;

public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/employee/register/{uid}")
	public ResponseEntity<?> registerEmployee(@PathVariable int uid,@RequestBody Employee employee,ResponseMessageDto dto) {
		// its used to insert the customer details i.e. to register customer
		User user = null;
		try {
			user = userService.validate(uid);
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
			}
		
		employee.setUser(user);
		employee =  employeeService.insert(employee);	
		 return ResponseEntity.ok(employee);
		 
	}
	
	@PutMapping("/employee/update/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable int id,@RequestBody Employee newEmployee ,ResponseMessageDto dto) {
		
		// validate id
		try {
			Employee existingEmployeeDb = employeeService.validate(id);
			if(newEmployee.getContactNumber()!=null)
				existingEmployeeDb.setContactNumber(newEmployee.getContactNumber());
			if(newEmployee.getAddress()!=null)
				existingEmployeeDb.setAddress(newEmployee.getAddress());
			if(newEmployee.getFirstName()!=null)
				existingEmployeeDb.setFirstName(newEmployee.getFirstName());
			if(newEmployee.getLastName()!=null) 
				existingEmployeeDb.setLastName(newEmployee.getLastName());
			
			

			//re save this existing customer having new updated value 
			existingEmployeeDb = employeeService.insert(existingEmployeeDb);
			return ResponseEntity.ok(existingEmployeeDb);
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
		
			
	}
	
	@PostMapping("/employee/customer/account/add/{cid}")
	public ResponseEntity<?> addAccount(@PathVariable int cid,@RequestBody Account accountDet,Account account ,ResponseMessageDto dto) {
		
		// validate customer id first;
		Customer customer = null;
	
	try {
		customer = customerService.validate(cid);
	} catch (ResourceNotFoundException e) {
		dto.setMsg(e.getMessage());
		return ResponseEntity.badRequest().body(dto);
	}
	account.setCustomer(customer);
	account.setAccountType(accountDet.getAccountType());
	account.setBalance(accountDet.getBalance());
	account.setDateCreated(LocalDate.now());
	
	account.setAadharNumber(accountDet.getAadharNumber());
	account.setPanNumber(accountDet.getPanNumber());
	
//	System.out.println(account);
	account = accountService.insert(account);
	return ResponseEntity.ok(account);	
	}
	
	@GetMapping("/employee/customer/account/details/{cid}")
	public ResponseEntity<?> getAccountDetails(@PathVariable int cid,ResponseMessageDto dto) {
		
		try {
			customerService.validate(cid);
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
		
		List<Account> list = accountService.getAccountDetails(cid);
		return ResponseEntity.ok(list);
		
	}
}
