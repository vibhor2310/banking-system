package com.springboot.banking_system.controller;

import com.springboot.banking_system.dto.ResponseMessageDto;
import com.springboot.banking_system.exception.ResourceNotFoundException;
import com.springboot.banking_system.model.Admin;
import com.springboot.banking_system.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	
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
   
}
