package com.springboot.banking_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.banking_system.model.Customer;
import com.springboot.banking_system.model.Employee;
import com.springboot.banking_system.model.User;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	@Query("select e from Employee e where e.id = ?1")
	List<Employee> getEmployeeDetail(int id);

}