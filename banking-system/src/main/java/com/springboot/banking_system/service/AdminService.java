package com.springboot.banking_system.service;

import com.springboot.banking_system.model.Admin;
import com.springboot.banking_system.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Optional<Admin> getAdminById(int id) {
        return adminRepository.findById(id);
    }

    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Optional<Admin> updateAdmin(int id, Admin adminDetails) {
        return adminRepository.findById(id).map(admin -> {
            admin.setFirst_name(adminDetails.getFirst_name());
            admin.setMiddle_name(adminDetails.getMiddle_name());
            admin.setLast_name(adminDetails.getLast_name());
            admin.setUser(adminDetails.getUser());
            return adminRepository.save(admin);
        });
    }

    public boolean deleteAdmin(int id) {
        return adminRepository.findById(id).map(admin -> {
            adminRepository.delete(admin);
            return true;
        }).orElse(false);
    }
}
