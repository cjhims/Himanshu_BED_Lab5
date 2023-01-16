package com.greatlearning.empmgmt.service;

import java.util.List;

import com.greatlearning.empmgmt.entity.Employee;

public interface EmployeeService {

	// Read
	List<Employee> findAll();

	// Create
	void save(Employee theTicket);

	// Delete
	void deleteById(int theId);

	// Update
	Employee findById(int theId);

}