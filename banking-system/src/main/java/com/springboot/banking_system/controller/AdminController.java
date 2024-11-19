package com.springboot.banking_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.banking_system.dto.ResponseMessageDto;
import com.springboot.banking_system.enums.InvestmentStatus;
import com.springboot.banking_system.exception.ResourceNotFoundException;
import com.springboot.banking_system.model.Admin;
import com.springboot.banking_system.model.Customer;
import com.springboot.banking_system.model.Investment;
import com.springboot.banking_system.model.Loan;
import com.springboot.banking_system.model.Transaction;
import com.springboot.banking_system.service.AdminService;
import com.springboot.banking_system.service.CustomerService;
import com.springboot.banking_system.service.InvestmentService;
import com.springboot.banking_system.service.LoanService;
import com.springboot.banking_system.service.TransactionService;

@RestController
public class AdminController {

	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private LoanService loanService;
	
	@Autowired
	private InvestmentService investmentService;
	
	@PostMapping("/admin/add")
	public void addAdmin(@RequestBody Admin admin) {
		adminService.insert(admin);
	}
	
	@GetMapping("/admin/all")
	public List<Admin> getAllAdmin() {
		List<Admin> list = adminService.getAllAdmin();
		return list;
	}
	
	@DeleteMapping("/admin/delete/{id}")
	public ResponseEntity<?> deleteAdmin(@PathVariable int id, ResponseMessageDto dto) {
		//validate id
		try {
			adminService.validate(id);
			adminService.delete(id);
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
		dto.setMsg("admin deleted");
		return ResponseEntity.ok(dto);
		
	}
	
	@PostMapping("/admin/batch/add")
	public List <Admin> batchInsert(@RequestBody List<Admin> list) {
		return adminService.insertInBatch(list);
	}
	
	
	@PutMapping("/admin/update/{id}")
	public ResponseEntity<?> updateAdmin(@PathVariable int id,@RequestBody Admin newAdmin, ResponseMessageDto dto) {
	
	try {
		Admin existingAdminDb= adminService.validate(id);
		if(newAdmin.getFirst_name()!=null)
			existingAdminDb.setFirst_name(newAdmin.getFirst_name());
		if(newAdmin.getMiddle_name()!=null)
			existingAdminDb.setMiddle_name(newAdmin.getMiddle_name());
		if(newAdmin.getLast_name()!=null)
			existingAdminDb.setLast_name(newAdmin.getLast_name());
		
		//re enter this existing admin having new updated value
		existingAdminDb = adminService.insert(existingAdminDb);
		return ResponseEntity.ok(existingAdminDb);
		
	} catch (ResourceNotFoundException e) {
		dto.setMsg(e.getMessage());
		return ResponseEntity.badRequest().body(dto);
	}
	}
	
	@GetMapping("admin/cutomers/all")
	public List<Customer> getAllCustomers(){
		List<Customer> list = customerService.showAllCustomers();
		return list;
	}
	
	@GetMapping("admin/alltransaction")
	public List<Transaction> getTransactions() {
		List<Transaction> list = transactionService.showAllTransaction();
		return list;
	}
	
	@GetMapping("admin/withdrawTrasaction")
	public List<Transaction> showWithdraw(){
	List<Transaction> list = transactionService.showWithdraw();
	return list;
	}
	
	@GetMapping("admin/showLoans")
	public List<Loan> showLoans()
	{
		List<Loan> list = loanService.showAll();
		return list;
	}
	
	@PutMapping("admin/updateLoan/{newInterestRate}")
	public void updateLoanInterestRate(@PathVariable double newInterestRate) {
		loanService.insertNewRate(newInterestRate);
		
	}
	
	@GetMapping("admin/showinvestments")
	public List<Investment> showAllInvestments(){
		List<Investment> list = investmentService.showAllInvestments();
		return list;
	}
	
//	@PutMapping("admin/investmentUpdate/{id}/{status}")
//	public ResponseEntity<ResponseMessageDto> updateInvestmentStatus(@PathVariable int id,
//			@PathVariable InvestmentStatus investmentStatus,
//			ResponseMessageDto dto) {
//		try {
//		investmentService.validate(id);
//		investmentService.updateInvestmentStatus(id,investmentStatus);
//		}catch(ResourceNotFoundException e) {
//			dto.setMsg(e.getMessage());
//			return ResponseEntity.badRequest().body(dto);
//		}
//		dto.setMsg("investment updated");
//		return ResponseEntity.ok(dto);
//	}
	

	
}
