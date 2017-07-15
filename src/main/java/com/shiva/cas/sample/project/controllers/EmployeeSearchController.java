package com.shiva.cas.sample.project.controllers;

import java.util.List;

import com.shiva.cas.sample.project.service.EmployeesSerachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shiva.cas.sample.project.models.Employee;

@RestController
@RequestMapping("/search")
public class EmployeeSearchController {

	@Autowired private EmployeesSerachService employeesSerachService;

	@RequestMapping(method = RequestMethod.GET) public List<Employee> getEmployee(@RequestParam(value = "key") String key) {
		return employeesSerachService.search(key);
	}

}