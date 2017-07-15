package com.shiva.cas.sample.project.controllers;

import java.util.List;

import com.shiva.cas.sample.project.models.Employee;
import com.shiva.cas.sample.project.service.EmployeesListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/list")
public class EmployeeListController {

	@Autowired private EmployeesListingService employeesListingService;

	@RequestMapping(method = RequestMethod.GET) public List<Employee> getEmployees() {
		return employeesListingService.listAll();
	}

}