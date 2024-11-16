package com.springboot.banking_system.service;


import com.springboot.banking_system.exception.ResourceNotFoundException;
import com.springboot.banking_system.model.Employee;
import com.springboot.banking_system.repository.EmployeeRepository;


	public Employee insert(Employee employee){
		
			return employeeRepository.save(employee);
			
		}

	public Employee validate(int eid) throws ResourceNotFoundException {
		
		Optional<Employee>optional = employeeRepository.findById(eid);
		
		if(optional.isEmpty()) {
			throw new ResourceNotFoundException("Given id is invalid try again...");
		}
		
		Employee employee = optional.get();
		
		return employee;
		
		
	}

	public void delete(int id) {
		employeeRepository.deleteById(id);
		
	}

	public List<Employee> getEmployeeDetail(int id) {
		return employeeRepository.getEmployeeDetail(id);
	}
}
