package com.greatlearning.empmgmt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.empmgmt.entity.Employee;
import com.greatlearning.empmgmt.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	// Read
	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	// Create
	@Override
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}

	// Delete
	@Override
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
	}

	// Update
	@Override
	public Employee findById(int theId) {
		Optional<Employee> result = employeeRepository.findById(theId);
		Employee theEmployee = null; 
		if (result.isPresent()) {
			theEmployee = result.get();
		} else {
			throw new RuntimeException("Did not find employee by id - " + theId);
		}
		return theEmployee;
	}
}
