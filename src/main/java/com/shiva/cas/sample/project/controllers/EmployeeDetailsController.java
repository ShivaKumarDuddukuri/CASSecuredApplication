package com.shiva.cas.sample.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shiva.cas.sample.project.models.Employee;
import com.shiva.cas.sample.project.service.EmployeeService;

@RestController
@RequestMapping("/details")
public class EmployeeDetailsController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(method = RequestMethod.GET)
	public Employee getDetails() {
		return employeeService.getDetails();
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Employee updateDetails(@RequestBody Employee employeeDetails) {
		return employeeService.updateDetails(employeeDetails);
	}

}