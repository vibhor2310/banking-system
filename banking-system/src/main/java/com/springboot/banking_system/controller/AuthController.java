package com.springboot.banking_system.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.banking_system.dto.ResponseMessageDto;
import com.springboot.banking_system.exception.InvalidUsernameException;
import com.springboot.banking_system.exception.ResourceNotFoundException;
import com.springboot.banking_system.model.User;
import com.springboot.banking_system.service.UserService;

@RestController
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/auth/sign-up")
	public ResponseEntity<?> signUp(@RequestBody User user,ResponseMessageDto dto){
		try {
			return ResponseEntity.ok(userService.signUp(user));
		} 
		catch (InvalidUsernameException e) {
			dto.setMsg(e.getMessage());
			 return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@GetMapping("/auth/login") 
	public ResponseEntity<?> login(Principal principal,ResponseMessageDto dto) {
		String username = principal.getName();
		User user = userService.findByUsername(username);
		if(!user.isEnabled()) {
			dto.setMsg("User disabled, Please contact Admin");
			return ResponseEntity.badRequest().body(dto);
		}
		return ResponseEntity.ok(user); 
	}
	
	@PostMapping("/auth/switch-status/{id}")
	public ResponseEntity<?> updateUserStatus(@PathVariable int id,
							     @RequestParam boolean status,
							     ResponseMessageDto dto) {
		try {
			User user = userService.updateUserStatus(id,status);
			return ResponseEntity.ok(user);  
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	

}
