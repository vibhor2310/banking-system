package com.springboot.banking_system.controller;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.banking_system.dto.ResponseMessageDto;
import com.springboot.banking_system.enums.InvestmentStatus;
import com.springboot.banking_system.enums.Type;
import com.springboot.banking_system.exception.ResourceNotFoundException;
import com.springboot.banking_system.model.Account;
import com.springboot.banking_system.model.Admin;
import com.springboot.banking_system.model.Customer;
import com.springboot.banking_system.model.Employee;
import com.springboot.banking_system.model.Investment;
import com.springboot.banking_system.service.AccountService;
import com.springboot.banking_system.service.AdminService;
import com.springboot.banking_system.service.CustomerService;
import com.springboot.banking_system.service.EmployeeService;
import com.springboot.banking_system.service.InvestmentService;

@RestController

public class InvestmentController {

    @Autowired
    private InvestmentService investmentService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/investment/{accountId}/{adminId}/{employeeId}")
    public ResponseEntity<?> processInvestment(@PathVariable int accountId, 
                                               @PathVariable int adminId, 
                                               @PathVariable int employeeId, 
                                               @RequestBody Investment investment,
                                               ResponseMessageDto dto) {
        
        // Validate and fetch Account object
        Account account = null;
        try {
            account = accountService.validate(accountId);
        } catch (ResourceNotFoundException e) {
            dto.setMsg("Account not found: " + e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }

        
        Admin admin = null;
        try {
            admin = adminService.validate(adminId);
        } catch (ResourceNotFoundException e) {
            dto.setMsg("Customer not found: " + e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }

       

        // Validate and fetch Employee object
        Employee employee = null;
        try {
            employee = employeeService.validate(employeeId);
        } catch (ResourceNotFoundException e) {
            dto.setMsg("Employee not found: " + e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }

        // Set investment details
        investment.setAccount(account);
        investment.setType(Type.MUTUALFUNDS);
        //investment.setAssignedEmployee(employee);
        investment.setPurchase_date(LocalDate.now());
        investment.setStatus(InvestmentStatus.PENDING);

        // Save the investment
        investment = investmentService.insert(investment);
        
        return ResponseEntity.ok(investment);
    }
}
