package com.greatlearning.empmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.greatlearning.empmgmt.entity.Employee;
import com.greatlearning.empmgmt.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/*
	 * Request mapping
	 * /employees - GET
	 * /employees/new
	 * /employees/edit/{id}
	 * /employees/delete/{id}
	 * /employees - POST
	 * /employees/{id} - POST
	 */
	
	@GetMapping("/employees")
	public String listEmployees(Model model) {
		List<Employee> result  = employeeService.findAll();
		model.addAttribute("employees", result);
		return "employee/list-employees";
	}

	@GetMapping("/employees/new")
	public String addNewEmployee(Model model) {
		Employee emp = new Employee();
		model.addAttribute("employee", emp);
		return "employee/create-employee";
	}

	@GetMapping("/employees/edit/{id}")
	public String updateEmployee(@PathVariable("id") Integer theId, Model model) {
		Employee emp = employeeService.findById(theId);
		model.addAttribute("employee", emp);
		return "employee/edit-employee";
	}
	
	@GetMapping("/employees/delete/{id}")
	public String deleteEmployee(@PathVariable("id") Integer theId) {
		employeeService.deleteById(theId);
		return "redirect:/employees";
	}
	
	@PostMapping("/employees")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		employeeService.save(theEmployee);
		return "redirect:/employees";
	}

	@PostMapping("/employees/{id}")
	public String updateEmployee(@ModelAttribute("employee") Employee theEmployee, @PathVariable("id") Integer theId) {
		Employee existingEmp = employeeService.findById(theId);
		if(existingEmp.getId() == theEmployee.getId() ) {
			existingEmp.setFirstName(theEmployee.getFirstName());
			existingEmp.setLastName(theEmployee.getLastName());
			existingEmp.setEmailAddress(theEmployee.getEmailAddress());
		}
		employeeService.save(existingEmp);
		return "redirect:/employees";
	}
}
